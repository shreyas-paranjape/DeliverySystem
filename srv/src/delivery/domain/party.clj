(ns delivery.domain.party
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [delivery.domain.entity :as ent]
            [delivery.infra.util :as util]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

;; Impl

;; API

(defn get-profile [party_id]
  (orm/select ent/party
              (orm/with ent/party_address)
              (orm/where {:id party_id})))

(defn update-profile []
  nil)

(defn insert-party [request]
  (do
    (def party_id (:generated_key (orm/insert ent/party (values (apply dissoc request [:role :sites :address :comm])))))
    (dorun
     (for [i (:comm request)]
       (do
         (def comm_id (:generated_key (orm/insert ent/comm (values i))))
         (orm/insert ent/party_comm (values {:party_id party_id :comm_id comm_id}))
         )
       )
     )
    (dorun
     (for [i (:address request)]
       (do
         (def address_id (:generated_key (orm/insert ent/address (values i))))
         (orm/insert ent/party_address (values {:party_id party_id :address_id address_id}))
         )
       )
     )
     (dorun
       (for [i (:sites request)]
         (do
           (def address_id (:generated_key (orm/insert ent/address (values (:address i)))))
           (def comm_id (:generated_key (orm/insert ent/comm (values (:comm i)))))
           (def site_id (:generated_key (orm/insert ent/site (values {:name (:name i) :address_id address_id :comm_id comm_id}))))
           (orm/insert ent/party_site (values {:party_id party_id :site_id site_id}))
           )
         )
       )
     (orm/insert ent/party_role (values {:role (:role request) :party_id party_id}))
     )
  )

;; Resources
(declare party-list-res party-res)
(defresource party-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (orm/select party))
             :post! (fn [ctx]
                          (insert-party (get-in ctx [:request :body :party]))
                          )
             :put! (fn [ctx]
                     ((orm/insert (util/request-body ctx))))
             :handle-created {:status "new entries added"})

(defresource party-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (get-profile
                            (get-in ctx [:request :params :party_id]))))

;; Routes
(defroutes routes
           (ANY "/party" request (party-list-res request))
           (ANY "/party/:party_id" request (party-res request)))
