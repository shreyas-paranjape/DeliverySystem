(ns srv.domain.order
  (:require [korma.core :as orm]
            [srv.domain.product :as prod]
            [liberator.core :refer [defresource]]
            [compojure.core :refer :all]))

;; Entities
(declare orderr order_item)
(orm/defentity orderr
               (orm/has-many order_item))
(orm/defentity order_item
               (orm/has-one prod/product))

;; Helper functions
(defn- insert-order [ord]
  (orm/insert orderr (orm/values ord)))

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
  (orm/update orderr
              (orm/set-fields {:status "D"})
              (orm/where {:id (:id order-to-cancel)})))

(defn get-order-history [customer_id]
  (orm/select orderr
              (orm/with order_item)
              (orm/where {:customer_id customer_id})))

;; Resources
(defresource order-all
             :available-media-types ["application/json"]
             :allowed-methods [:get :put])

(defresource order-single
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :delete])

;; Routes
(defroutes routes
           (ANY "/order" request (order-all request))
           (ANY "/order/:order_id" request (order-single request)))

