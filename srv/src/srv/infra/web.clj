(ns srv.infra.web
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer
             [wrap-defaults site-defaults]]
            [ring.middleware.json :refer
             [wrap-json-response wrap-json-body]]
            [ring.middleware.params :refer
             [wrap-params]]
            [srv.domain.customer :as cust]
            [srv.domain.order :as ord]
            [srv.domain.product :as prod]))

;; ROUTES
(defroutes home
           (GET "/" request (str request)))
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
(def app
  ;;(wrap-defaults
  (wrap-json-body
    (wrap-json-response
      (wrap-params
        app-routes))
    {:keywords? true}))
;;site-defaults)



