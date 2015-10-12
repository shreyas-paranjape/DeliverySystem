(ns delivery.infra.util
  (:require [taoensso.timbre :as timbre]
            [lobos.schema :as sqlType]))

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


;; Reusable columns
(defn col-surrogate-key [table]
  (sqlType/integer table :id :auto-inc :primary-key))

(defn col-datetime-tracked [table]
  (-> table
      (sqlType/timestamp :updated_on)
      (sqlType/timestamp :created_on (sqlType/default (now)))))

(defn col-name-desc [table]
  (-> table
      (sqlType/varchar :name 100 :not-null)
      (sqlType/text :description)))

;; abstract table def macros
(defmacro tbl-base [name & elements]
  `(-> (sqlType/table ~name
                      (col-surrogate-key)
                      (col-datetime-tracked))
       ~@elements))

(defmacro tbl-base-name-desc [name & elements]
  `(-> (sqlType/table ~name
                      (col-surrogate-key)
                      (col-datetime-tracked)
                      (col-name-desc))
       ~@elements))