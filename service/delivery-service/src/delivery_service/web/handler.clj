(ns delivery-service.web.handler
  (:require [ring.middleware.defaults :refer :all]
            [delivery-service.web.route :as r]
            [taoensso.carmine.ring :refer [carmine-store]]
            [ring.middleware.json :refer 
             [wrap-json-response wrap-json-body]]
            [taoensso.timbre :as timbre]
            [ring.middleware.params :refer [wrap-params]]))

(timbre/refer-timbre)

(def app
  (wrap-json-body
   (wrap-json-response (wrap-params r/app-routes))
   {:keywords? true}))
