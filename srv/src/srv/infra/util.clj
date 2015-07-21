(ns srv.infra.util
  (:require [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

(defn request-body [ctx]
  (str (get-in ctx [:request :params])))

(defn log-request [ctx]
  (timbre/debug "Request : " + (request-body ctx)))

