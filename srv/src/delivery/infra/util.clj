(ns delivery.infra.util
  (:require [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

(defn request-body [ctx]
   (get-in ctx [:request :params :root]))

(defn log-request [ctx]
  (timbre/debug "Request : " + (request-body ctx)))

(def valid-chars
  (map char (concat (range 48 58)                           ; 0-9
                    (range 65 91)                           ; A-Z
                    (range 97 123))))                       ; a-z

(defn random-string [length]
  (apply str (take length (repeatedly #(rand-nth valid-chars)))))

(defn random-number [length]
  (apply str (take length (repeatedly #(rand-nth (range 0 9))))))