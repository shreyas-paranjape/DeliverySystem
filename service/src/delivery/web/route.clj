(ns delivery.web.route
  (:use [ring.util.response])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [delivery.web.resource :as res]))

(timbre/refer-timbre)

(defroutes app-routes
  (ANY "/" [_] "Welcome")
  (ANY "/get-time-stamp-latest" request (res/getts request))
  (ANY "/get-menu" request (res/getmenu request))
  (ANY "/customer/email_ver/:hash" request (res/ver_email_customer request))
  (ANY "/customer/mobile_ver/:number" request (res/ver_mobile_customer request))
  (ANY "/customer/:person_id" request (res/customer request request))
  (ANY "/customer/:person_id/add-items" request (res/create-plan request request))
  (ANY "/customer/:person_id/order/:order_id" request (res/order_specific request))
  (ANY "/custmer/:person_id/order" request (res/order request))
  (ANY "/product/:site_id" request (res/product request))
  (ANY "/product/:site_id/:product_id" request (res/product_specific request))
  )

