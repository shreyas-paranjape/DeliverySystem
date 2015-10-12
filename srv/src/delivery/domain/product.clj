(ns delivery.domain.product
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]
            [delivery.infra.util :as util]
            [delivery.domain.schema :as schema])
  (:import (java.util Date)))

(declare products-res product-res product-category-res product-categories-res
         get-products)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;; PRODUCT CATEGORY ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn- get-root-category []
  (first (orm/select product_category
                     (orm/where {:lft 1 :active 1}))))

(defn- get-category [id]
  (first
    (orm/select product_category
                (orm/where {:id id :active 1}))))

(defn- get-sub-categories [category]
  (orm/exec-raw ["CALL get_sub_cat(?)"
                 [(:id category)]]
                :results))

(defn- get-category-all [parent getProducts? getSubcategories?]
  (if (= true getProducts?)
    (if (= true getSubcategories?)
      (assoc parent
        :subCategories (map get-category-all
                            (get-sub-categories parent)
                            (repeatedly #(= getProducts? true))
                            (repeatedly #(= getSubcategories? true)))
        :products (get-products {:product_category_id (:id parent)}))
      (assoc parent
        :products (get-products {:product_category_id (:id parent)})))
    (if (= true getSubcategories?)
      (assoc parent
        :subCategories (map get-category-all
                            (get-sub-categories parent)
                            (repeatedly #(= getProducts? true))
                            (repeatedly #(= getSubcategories? true)))))))

(defn- ins-prod-cat [prod-cat]
  (orm/exec-raw ["CALL ins_prod_cat(?,?,?)"
                 [(:name prod-cat),
                  (:description prod-cat)
                  (:parent_id prod-cat)]]))

(defn- upd-prod-cat [prod-cat]
  (orm/update product_category
              (orm/set-fields (assoc (dissoc prod-cat :parent_id) :updated_on (Date.)))
              (orm/where {:id (:id prod-cat)})))

(defn- del-prod-cat [prod-cat]
  (orm/exec-raw ["CALL del_prod_cat(?)"
                 [(:id prod-cat)]]))



;;(defn- get-leaf-categories []
;;  (orm/exec-raw ["SELECT * FROM product_category where rgt = lft + 1"] :results))

;;(defn- get-leaf-category-ids []
;;  (map #(:id %) (get-leaf-categories)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;; PRODUCT ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def not-nil? (complement nil?))

(defn product-for-cat [base product_category_id include_sub]
  (if (not-nil? product_category_id)
    (if (true? include_sub)
      (let [sub-cat (get-sub-categories (get-category product_category_id))]
        (-> base
            (orm/where {:product_category_id [in (map #(:id %) sub-cat)]})
            (orm/select)))
      (-> base
          (orm/where {:product_category_id product_category_id})
          (orm/select)))
    (-> base
        (orm/select))))

; { :product_category_id 1 :include_sub true :party_id 12 :product_id 1232 }
(defn get-products
  [query]
  (let [base (-> (orm/select* product)
                 (orm/with product_category)
                 (orm/with product_party
                           (orm/with party))
                 (orm/order :id))
        ;(orm/limit (:limit query))
        ;(orm/offset (dec (:offset query))))
        {product_id          :product_id
         party_id            :party_id
         product_category_id :product_category_id
         include_sub         :include_sub} query]
    (if (not-nil? product_id)
      (-> base
          (orm/where {:product_id product_id})
          (orm/select))
      (if (not-nil? party_id)
        (product-for-cat
          (-> base
              (orm/with product_party
                        (orm/with party)
                        (orm/where {:party_id party_id})))
          product_category_id
          include_sub)
        (product-for-cat
          base
          product_category_id
          include_sub)))))

(defn insert-product [prod]
  (timbre/info prod)
  (let [new_prod_id
        (:generated_key
          (orm/insert product
                      (orm/values (select-keys prod [:name :product_category_id]))))]
    (orm/insert product_party
                (orm/values
                  (assoc
                    (select-keys
                      prod
                      [:description :image_url :preparation_time :price])
                    :product_id new_prod_id)))
    new_prod_id))

(defn- update-product [prod]
  prod)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Resources ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defresource product-categories-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post]
             :handle-ok
             (fn [ctx]
               (get-category-all (get-root-category) (get-in ctx [:request :params :product]) true))
             :post!
             (try
               (fn [ctx]
                 (util/validate-fn
                   schema/Product-Category
                   (get-in ctx [:request :params])
                   ins-prod-cat))
               (catch Exception e (timbre/info (str "caught exception: " (.getMessage e)))))
             :handle-created
             {:status "New product category added"})

(defresource product-category-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :put :delete]
             :handle-ok
             (fn [ctx]
               (let [{id :id product :getProduct subCategory :getSubCategory} (get-in ctx [:request :params])]
                 (get-category-all (get-category id) product subCategory)))
             :put!
             (try
               (fn [ctx]
                 (util/validate-fn
                   schema/Product-Category
                   (get-in ctx [:request :params])
                   upd-prod-cat))
               (catch Exception e
                 (timbre/info (str "caught exception: " (.getMessage e)))))
             :delete!
             (try
               (fn [ctx]
                 (del-prod-cat (get-in ctx [:request :params])))
               (catch Exception e
                 (timbre/info (str "caught exception: " (.getMessage e))))))

(defresource products-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok
             (fn [ctx]
               (get-products (get-in ctx [:request :body])))
             :post!
             (fn [ctx]
               (util/validate-fn
                 schema/Product
                 (get-in ctx [:request :params])
                 insert-product))
             :handle-created {:status "New product has been added"})

(defresource product-res
             :available-media-types ["application/json"]
             :allowed-methods [:get :put :delete]
             :handle-ok
             (fn [ctx]
               (get-products (get-in ctx [:request :body])))
             :put!
             (fn [ctx]
               (util/validate-fn
                 schema/Product
                 (get-in ctx [:request :params])
                 update-product)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Routes ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defroutes routes
           (ANY "/product_category" request (product-categories-res request))
           (ANY "/product_category/:id" request (product-category-res request))
           (ANY "/product" request (products-res request))
           (ANY "/product/:id" request (product-res request)))


