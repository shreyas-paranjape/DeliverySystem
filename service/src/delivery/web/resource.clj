(ns delivery.web.resource
  (:require [liberator.core :refer [defresource]]
            [taoensso.timbre :as timbre]
            [delivery.domain.customer :as customer]
            [delivery.domain.orders :as orders]
            [delivery.domain.product :as product]))

(timbre/refer-timbre)
(timbre/merge-config! {:level :debug})


(defresource customer
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
               (customer/get-profile (get-in ctx [:request]))
               )
  :put! (fn [ctx]
  	(customer/update-profile (get-in ctx [:request])))
  )

(defresource create-plan
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :post! (fn [ctx]
      (orders/create-plan (get-in ctx [:request]))
    )
  )

(defresource order_specific
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
    (orders/get_order (get-in ctx [:request])) 
    )
  :post! (fn [ctx]
    (orders/place_order (get-in ctx [:request]))
    )
  :delete (fn [ctx]
    (orders/delete_order (get-in ctx [:request]))
    )
  )

(defresource order
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
    (orders/get_all_orders (get-in ctx [:request])))
  )

(defresource product_specific
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
  	(if (:id (:product (get-in ctx [:request]))) (product/get_specific_product (get-in ctx [:request ])))
  	(if (or (:price_range (get-in ctx [:request])) (:user_rating (get-in ctx [:request]))) (product/search_p (get-in ctx [:request]))))
  :post! (fn [ctx]
  	(product/add_p (get-in ctx [:request])))
  :put! (fn [ctx]
  	(product/update_p (get-in ctx [:request])))
  :delete (fn [ctx]
  	(product/delete_p (get-in ctx [:request])))
  )

(defresource product
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
  	(product/get_all_products (get-in ctx [:request])))
  )

(defresource ver_email_customer
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-created! (fn [ctx]
                (customer/verify_email (get-in ctx [:request]))
                )
  )

(defresource ver_mobile_customer
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-created! (fn [ctx]
                (customer/verify_mobile (get-in ctx [:request]))
                )
  )