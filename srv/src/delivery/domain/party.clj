(ns delivery.domain.party
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [schema.core :as s]
            [delivery.domain.schema :as schema]
            [delivery.infra.util :as util]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]
            [delivery.infra.util :as util]
            [schema.core :as clType]))

(timbre/refer-timbre)

;; API
(defn get-profile [party_id]
  (orm/select party
              (orm/with party_address
                        (orm/with address))
              (orm/with party_role)
              (orm/with party_comm
                        (orm/with comm))
              (orm/where {:party.id party_id})))

(defn get-all-profiles [party_query]
  (do
    (def results [])
    (dorun
      (for [i (:products party_query)]
        (do
          (def results
            (into results
                  (orm/select party
                              (orm/with party_address
                                        (orm/with address))
                              (orm/with party_role)
                              (orm/with party_comm
                                        (orm/with comm))
                              (orm/with product_party
                                        (orm/with product))
                              (orm/where {:product.id i :party_role.role (:role party_query)})
                              (orm/limit (:count party_query))
                              (orm/offset (dec (:start_id party_query)))))))))
    (def results (vec (distinct results)))
    results))

(defn update-profile []
  nil)

(defn insert-party [request]
  (do
    (def party_id (:generated_key (orm/insert party (orm/values (apply dissoc request [:role :sites :address :comm])))))
    (dorun
      (for [i (:comm request)]
        (do
          (def comm_id (:generated_key (orm/insert comm (orm/values i))))
          (orm/insert party_comm (orm/values {:party_id party_id :comm_id comm_id})))))
    (dorun
      (for [i (:address request)]
        (do
          (def address_id (:generated_key (orm/insert address (orm/values i))))
          (orm/insert party_address (orm/values {:party_id party_id :address_id address_id})))))
    (dorun
      (for [i (:sites request)]
        (do
          (def address_id (:generated_key (orm/insert address (orm/values (:address i)))))
          (def comm_id (:generated_key (orm/insert comm (orm/values (:comm i)))))
          (def site_id (:generated_key (orm/insert site (orm/values {:name (:name i) :address_id address_id :comm_id comm_id}))))
          (orm/insert party_site (orm/values {:party_id party_id :site_id site_id})))))
    (orm/insert party_role (orm/values {:role (:role request) :party_id party_id}))
    (socket/send-msg "order was posted")))

;; Resources
(declare party-list-res party-res)

(defresource party-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (get-profile
                            (get-in ctx [:request :params :party_id])))
             :put! (fn [ctx]
                     ((orm/insert (util/request-body ctx))))
             :handle-created {:status "new entries added"})

(defresource party-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (get-all-profiles (get-in ctx [:request :body :party_query])))
             :handle-created (fn [ctx]
                               (util/validate-fn (:struct schema/Party) (get-in ctx [:request :body :party]) insert-party)))

;; Routes
(defroutes routes
           (ANY "/party" request (party-list-res request))
           (ANY "/party/:party_id" request (party-res request)))
