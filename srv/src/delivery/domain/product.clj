(ns delivery.domain.product
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [clojure.java.jdbc :as j]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

;; Functions
(defn- get-root-category []
  (first (orm/select product_category
                     (orm/where {:lft 1}) (orm/order :id :ASC))))

(defn- get-category [name]
  (first (orm/select product_category
                     (orm/where {:name name}))))

(defn- get-sub-categories [category]
  (orm/exec-raw ["CALL get_sub_cat(?)"
                 [(:id category)]]
                :results))

(defn- get-products-for-category [query]
  (if (= (:include_sub_categories (:category query)) "false")
  (orm/select product
              (orm/with product_category)
              (orm/with product_party
                (orm/with party))
              (orm/where {:product_category_id (:product_category_id (:category query)) :party.id (:party_id (:party query))})
              (orm/limit (:count query))
              (orm/offset (dec (:start_id query)))))
  
  ; Else condition a bit of a problem
  (j/query db ["SELECT distinct * FROM nested_category AS node,
                      nested_category AS parent,
                      nested_category 
                      inner join products on 
                      nested_category.id=products.nested_category_id 
                      WHERE node.lft BETWEEN parent.lft AND parent.rgt AND parent.id=2 
                      ORDER BY node.lft" ])
  )

(defn insert-product [request]
  (do
    (def product_id (:generated_key (orm/insert (orm/values (apply dissoc request [:parties])))))
    (dorun
      (for [i (:parties request)]
        (do
          (orm/insert product_party (orm/values (conj i {:product_id product_id})))
          )
        )
      )
    )
  )

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
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                                  (get-products-for-category (get-in ctx [:request :body :product]))
                                  )
             :post! (fn [ctx]
                          (insert-product (get-in ctx [:request :body :product]))
                          )
             :handle-created {:status "New product has been added"}
             )

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
