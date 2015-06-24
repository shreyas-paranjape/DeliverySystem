(ns delivery.web.route
  (:use [ring.util.response])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [delivery.web.resource :as res]))

(timbre/refer-timbre)

(defroutes app-routes
   (ANY "/" [_] "Welcome")
  (ANY "/customer/:person_id" [person_id request] (res/customer (conj request {:body (conj (:body request) {:person {:id person_id}} )})))
  (ANY "/customer/:id/add-items" request (res/create-plan request))
  (ANY "/customer/:person_id/order/:order_id" [person_id order_id request] (res/order_specific (conj request {:body (conj (:body request) {:person {:id person_id}} {:order {:id order_id}})})))
  (ANY "/custmer/:person_id/order" [person_id request] (res/order (conj request {:body (conj (:body request) {:person {:id person_id}})})))
  (ANY "/product/:site_id" [site_id request] (res/product (conj request {:body (conj (:body request) {:product {:site_id site_id}})})))
  (ANY "/product/:site_id/:product_id" [site_id product_id request] (res/product_specific (conj request {:body (conj (:body request) {:product {:site_id site_id, :id product_id}})})))
  )

