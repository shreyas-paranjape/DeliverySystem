(ns delivery.infra.web
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer
             [wrap-defaults site-defaults]]
            [ring.middleware.json :refer
             [wrap-json-response wrap-json-body wrap-json-params]]
            [ring.middleware.params :refer
             [wrap-params]]
            [delivery.domain.party :as cust]
            [delivery.domain.order :as ord]
            [delivery.domain.product :as prod]
            [delivery.infra.middleware :as mw]
            [delivery.infra.socket :as socket]))

(defn dummy [request]
  (socket/send-msg "i rule"))

;; ROUTES
(defroutes home
           (GET "/" request (str request))
           (GET "/dummy" [] dummy)
           (ANY "/connect" request (socket/socket-handler request)))

(defroutes not-found
           (route/not-found "Not Found"))

(def app-routes
  (routes
    home
    cust/routes
    ord/routes
    prod/routes
    not-found))

;; APPLICATION
(defn app [& request]
  (wrap-defaults
    (->  app-routes
         mw/keywordize-params
         wrap-params
         wrap-json-response
         wrap-json-params
         wrap-json-body) site-defaults))
