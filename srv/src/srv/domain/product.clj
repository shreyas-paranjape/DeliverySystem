(ns srv.domain.product
  (:require [srv.infra.db :as db]
            [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

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
(defn- get-root-category []
  (first (orm/select product_category
                     (orm/where {:lft 1}))))

(defn- get-category [name]
  (first (orm/select product_category
                     (orm/where {:name name}))))

(defn- get-sub-categories [category]
  (orm/exec-raw ["CALL get_sub_cat(?)"
                 [(:id category)]]
                :results))

(defn- get-products-for-category [category]
  (orm/select product
              (orm/where {:product_category_id (:id category)})))

(defn- get-category-all [parent]
  (assoc parent
    :subCategories (map get-category-all (get-sub-categories parent))
    :products (get-products-for-category parent)))

;; Functions
(defn- get-product-suppliers [product_id]
  (def product_supplier_ids
    (orm/select product_supplier
                (orm/where {:product_id product_id})))
  (map #(orm/select supplier (orm/where {:id %})) product_supplier_ids))


;;(defn- get-leaf-categories []
;;  (orm/exec-raw ["SELECT * FROM product_category where rgt = lft + 1"] :results))

;;(defn- get-leaf-category-ids []
;;  (map #(:id %) (get-leaf-categories)))

;; API
(defn get-catalogue [root-cat]
  (get-category-all
    (if (not (nil? root-cat))
      (get-category (:name root-cat))
      (get-root-category))))


;; Resource
(declare product-all product-single catalogue-all catalogue-food)
(defresource product-all
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

(defresource product-single
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

(defresource catalogue-all
             :available-media-types ["application/json"]
             :allowed-methods [:get]
             :handle-ok (fn [ctx]
                          (get-catalogue (get-in ctx [:request :params]))))

;; Routes
(defroutes routes
           (ANY "/product" request (product-all request))
           (ANY "/product/:product_id" request (product-single request))
           (ANY "/catalogue" request (catalogue-all request)))

