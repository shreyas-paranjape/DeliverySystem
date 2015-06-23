(ns delivery-service.web.route
  (:use [ring.util.response])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [delivery-service.web.resource :as res]))

(timbre/refer-timbre)

(defroutes app-routes
  (ANY "/customer/:id" [id request] (res/customer (conj request {:body (conj (:body request) {:person {:id id}} )})))
  (ANY "/customer/:id/add-items" request (res/create-plan request))
  (ANY "/customer/:id/order/:id2" [id id2 request] (res/order_specific (conj request {:body (conj (:body request) {:person {:id id}} {:order {:id id2}})})))
  (ANY "/custmer/:id/order" [id request] (res/order (conj request {:body (conj (:body request) {:person {:id id}})})))
  (ANY "/product/:id" [id request] (res/product (conj request {:body (conj (:body request) {:product {:site_id id}})})))
  (ANY "/product/:id/:id2" [id id2 request] (res/product_specific (conj request {:body (conj (:body request) {:product {:site_id id, :id id2}})})))
  )

