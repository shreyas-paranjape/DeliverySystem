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
            [delivery.middleware.keywordize :as mw]))



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
    (wrap-json-params
      (wrap-json-response
        (wrap-params
          (mw/keywordize-params
            app-routes))))
    :keywords? true))
;;site-defaults)