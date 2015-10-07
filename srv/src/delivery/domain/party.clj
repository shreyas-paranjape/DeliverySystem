(ns delivery.domain.party
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
              [delivery.infra.util :as util]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

;; Impl

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
          (def results (into results (orm/select party
              (orm/with party_address
                (orm/with address))
              (orm/with party_role)
              (orm/with party_comm
                (orm/with comm))
              (orm/with product_party
                (orm/with product))
              (where {:product.id i :party_role.role (:role party_query)})
              (limit (:count party_query))
              (offset (dec (:start_id party_query)))
              )))
          )
        )
      )
    (def results (vec (distinct results)))
    results
    )
  )

(defn update-profile []
  nil)

(defn insert-party [request]
  (do
    (def party_id (:generated_key (orm/insert party (values (apply dissoc request [:role :sites :address :comm])))))
    (dorun
     (for [i (:comm request)]
       (do
         (def comm_id (:generated_key (orm/insert comm (values i))))
         (orm/insert party_comm (values {:party_id party_id :comm_id comm_id}))
         )
       )
     )
    (dorun
     (for [i (:address request)]
       (do
         (def address_id (:generated_key (orm/insert address (values i))))
         (orm/insert party_address (values {:party_id party_id :address_id address_id}))
         )
       )
     )
     (dorun
       (for [i (:sites request)]
         (do
           (def address_id (:generated_key (orm/insert address (values (:address i)))))
           (def comm_id (:generated_key (orm/insert comm (values (:comm i)))))
           (def site_id (:generated_key (orm/insert site (values {:name (:name i) :address_id address_id :comm_id comm_id}))))
           (orm/insert party_site (values {:party_id party_id :site_id site_id}))
           )
         )
       )
     (orm/insert party_role (values {:role (:role request) :party_id party_id}))
     )
  )

;; Resources
(declare party-list-res party-res)

(defresource party-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (get-all-profiles (get-in ctx [:request :body :party_query])))
             :put! (fn [ctx]
                     ((orm/insert (util/request-body ctx))))
             :handle-created {:status "new entries added"})

(defresource party-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (get-profile
                            (get-in ctx [:request :params :party_id])))
             :post! (fn [ctx]
                          (insert-party (get-in ctx [:request :body :party]))
                          ))

;; Routes
(defroutes routes
           (ANY "/party" request (party-list-res request))
           (ANY "/party/:party_id" request (party-res request)))
