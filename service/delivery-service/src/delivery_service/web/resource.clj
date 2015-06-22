(ns delivery-service.web.resource
  (:require [liberator.core :refer [defresource]]
            [taoensso.timbre :as timbre]
            [delivery-service.domain.customer :as customer]
            [delivery-service.domain.order :as order]))

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

(defresource order
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :handle-ok (fn [ctx]
    (if (:id (:order request)) (order/get_order (get-in ctx [:request :body])) (order/get_all_orders (get-in ctx [:request :body])))
    )
  :post! (fn [ctx]
    (order/place_order (get-in ctx [:request :body]))
    )
  :delete (fn [ctx]
    (order/delete_oder (get-in ctx [:request :body]))
    )
  )