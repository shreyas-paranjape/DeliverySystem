(ns delivery.domain.order
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [schema.core :as s]
            [delivery.domain.schema :as schema]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY GET POST PUT DELETE defroutes]]
            [taoensso.timbre :as timbre]
            [delivery.infra.util :as util])
  (:import (java.util UUID)))

(timbre/refer-timbre)

;; Impl
(defn- insert-order [order]
  (do
    (def ordr_id
      (:generated_key
        (orm/insert ordr
                    (orm/values {:code (str (UUID/randomUUID))}))))
    (orm/insert order_party (orm/values {:party_id order}))
    (dorun
      (for [i (:orders order)]
        (do
          (orm/insert order_item (orm/values (conj {:ordr_id ordr_id} i))))))))

(defn- insert-order-item [new-order-item]
  (orm/insert order_item
              (orm/values new-order-item)))

(defn- assoc-order-id [order_items order_id]
  (map #(assoc-in % [:order_id] order_id) order_items))


;; API
(defn place [new-order]
  (def order_id (:generated_key (insert-order (dissoc new-order :order_items))))
  (map insert-order-item (assoc-order-id
                           (get-in new-order [:order_items]) order_id)))

(defn cancel [order-to-cancel]
  (orm/update ordr
              (orm/set-fields {:status "D"})
              (orm/where {:id (:id order-to-cancel)})))

(defn get-order-history [customer_id]
  (orm/select ordr
              (orm/with order_item)
              (orm/where {:customer_id customer_id})))

;; Resources
(declare order-list-res order-list-put-res order-res)
(defresource order-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :delete]
             :handle-created (fn [ctx]
                               (util/validate-fn
                                 (:struct schema/Order)
                                 (get-in ctx [:request :body :order])
                                 insert-order)))

(defresource order-list-put-res
             :available-media-types ["application/json"]
             :allowed-methods [:put])

(defresource order-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :put :delete])

;; Routes
(defroutes routes
           (POST "/order" request (order-list-res request))
           (GET "/order" request (order-list-res request))
           (DELETE "/order" request (order-list-res request))
           (PUT "/order" request (order-list-put-res request))
           (ANY "/order/:order_id" request (order-res request)))
