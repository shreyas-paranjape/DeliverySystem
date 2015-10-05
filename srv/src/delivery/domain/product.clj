(ns delivery.domain.product
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

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
(declare product-list-res product-res catalogue-res)
(defresource product-list-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

(defresource product-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete])

(defresource catalogue-res
             :available-media-types ["application/json"]
             :allowed-methods [:get]
             :handle-ok 
                (fn [ctx]
                  (get-catalogue (get-in ctx [:request :params]))))

;; Routes
(defroutes routes
           (ANY "/product" request (product-list-res request))
           (ANY "/product/:product_id" request (product-res request))
           (ANY "/catalogue" request (catalogue-res request)))
