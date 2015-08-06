(ns srv.infra.comm
  (:require [postal.core :as mailer]
            [srv.infra.util :as util]
            [org.httpkit.client :as http])
  (:import (java.net URLEncoder)))

;; Email
(def email "admin@foodamigo.co.in")
(def pass "")
(def conn {:host "smtp.gmail.com"
           :ssl  true
           :user email
           :pass pass})

(defn send-mail [to rand-string]
  (mailer/send-message conn
                       {:from    "admin@foodamigo.co.in"
                        :to      to
                        :subject "Email verification for FoodAmigo"
                        :body    (str "Thank you for registering with FoodAmigo.\n
                                 Before you can order click below link to verify
                                 your email.\n" rand-string)}))

;; SMS
(def sms-api-url "https://alerts.solutionsinfini.com/api/v3/index.php")
(def fixed-params {"method"  "sms",
                   "sender"  "CYBCAD",
                   "format"  "json",
                   "custom"  (util/random-number 3),
                   "flash"   "0"
                   "api_key" ""})

(defn- encode-params [request-params]
  (let [encode #(URLEncoder/encode (str %) "UTF-8")
        coded (for [[n v] request-params] (str (encode n) "="
                                               (encode v)))]
    (apply str (interpose "&" coded))))

(defn- make-request-url [params]
  (str sms-api-url "?" (encode-params params)))

(defn send-sms [to msg]
  (http/post (make-request-url (into fixed-params {"to" to "message" msg}))))
