(ns delivery-service.web.route
  (:use [ring.util.response])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [delivery-service.web.resource :as res]))

(timbre/refer-timbre)

(defroutes app-routes
  (ANY "/api/site" request (res/site-res request)))

