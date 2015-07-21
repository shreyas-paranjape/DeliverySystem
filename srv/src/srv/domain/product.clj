(ns srv.domain.product
  (:require [srv.infra.db :as db]
            [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer :all]))

;; Entities
(declare product product_category product_supplier supplier)

(orm/defentity product_category)

(orm/defentity product
               (orm/belongs-to product_category)
               (orm/has-many product_supplier))

(orm/defentity product_supplier
               (orm/has-one supplier))

(orm/defentity supplier
               (orm/has-one db/address))

;; Functions
(defn- get-products [category_id]
  (orm/select product
              (orm/where {:product_category_id category_id})))

(defn- get-all-categories-ids []
  (map #(:id %) (orm/select product_category)))

(defn- get-product-suppliers [product_id]
  (def product_supplier_ids (orm/select product_supplier
                                        (orm/where {:product_id product_id})))
  (map #(orm/select supplier (orm/where {:id %})) product_supplier_ids))

;; API
(defn get-all []
  (map get-products (get-all-categories-ids)))

;; Resource
(defresource product-all
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

(defresource product_single
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

;; Routes
(defroutes routes
           (ANY "/product" request (product-all request))
           (ANY "/product/:product_id" request (product-single request)))

