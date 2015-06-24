(ns delivery.web.resource
  (:require [liberator.core :refer [defresource]]
            [taoensso.timbre :as timbre]
            [delivery.domain.customer :as customer]
            [delivery.domain.orders :as orders]
            [delivery.domain.order :as product]))

(timbre/refer-timbre)
(timbre/merge-config! {:level :debug})


(defresource customer 
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
               (debug (get-in ctx [:request :body]))
               (customer/get-profile (get-in ctx [:request :body]))
               )
  :put! (fn [ctx]
  	(customer/update-profile (get-in ctx [:request :body])))
  )

(defresource create-plan
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :post! (fn [ctx]
      (order/create-plan (get-in ctx [:request :body]))
    )
  )

(defresource order_specific
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
    (orders/get_order (get-in ctx [:request :body])) 
    )
  :post! (fn [ctx]
    (orders/place_order (get-in ctx [:request :body]))
    )
  :delete (fn [ctx]
    (orders/delete_oder (get-in ctx [:request :body]))
    )
  )

(defresource order
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
    (orders/get_all_orders (get-in ctx [:request :body])))
  )

(defresource product_specific
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
  	(if (:id (:product (get-in ctx [:request :body]))) (product/get_specific_product (get-in ctx [:request :body])))
  	(if (or (:price_range (get-in ctx [:request :body])) (:user_rating (get-in ctx [:request :body]))) (product/search (get-in ctx [:request :body]))))
  :post! (fn [ctx]
  	(product/add (get-in ctx [:request :body])))
  :put! (fn [ctx]
  	(product/update (get-in ctx [:request :body])))
  :delete (fn [ctx]
  	(product/delete (get-in ctx [:request :body])))
  )

(defresource product
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
  	(product/get_all_products (get-in ctx [:request :body])))
  )