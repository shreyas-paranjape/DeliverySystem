(ns delivery.infra.socket
  (:require
    [org.httpkit.server :as srv]
    [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

(def clients (atom {})) ;; a hub, a map of client => sequence number

(defn- on-connect [channel]
  (debug channel "connected")
  (swap! clients assoc channel true))

(defn send-msg [data]
  (doseq [client (keys @clients)]
    (srv/send! client data)))

(defn socket-handler [request]
  (srv/with-channel request channel
    (on-connect channel)
    (srv/on-close channel
      (fn [status]
        (swap! clients dissoc channel)
        (debug "channel closed: " status)))
    (srv/on-receive channel
      (fn [data]
        (debug "recieved data: " data)))))
