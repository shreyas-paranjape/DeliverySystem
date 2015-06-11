(ns delivery-service.web.resource
  (:require [liberator.core :refer [defresource]]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/merge-config! {:level :debug})


(defresource site-res
  :available-media-types ["application/json"]
  :allowed-methods [:get]
  :handle-ok (fn [ctx]
               (debug (get-in ctx [:request :body]))
               {"message" "hello-world"}))
