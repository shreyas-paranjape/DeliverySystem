(ns delivery.domain.order
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

;; Impl
(defn- insert-order [ord]
  (orm/insert ordr (orm/values ord)))

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
(defresource order-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post])

(defresource order-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :put :delete])

;; Routes
(defroutes routes
           (ANY "/order" request (order-list-res request))
           (ANY "/order/:order_id" request (order-res request)))
