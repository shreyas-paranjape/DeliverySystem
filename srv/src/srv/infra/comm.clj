(ns srv.infra.comm
  (:require [postal.core :as mailer]))

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

(defn send-sms [to otp]
  nil)
