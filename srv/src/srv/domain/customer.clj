(ns srv.domain.customer
  (:require [korma.core :as orm]
            [srv.infra.db :as db]
            [srv.infra.util :as util]
            [srv.infra.comm :as comm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer :all]))


;; TODO Verify access token from google server
;; TODO Refresh expired tokens
;; TODO Verify mobile number
;; TODO address verified on first delivery

;; Entity
(declare customer customer_address)

(orm/defentity customer_address
               (orm/has-one db/address))

(orm/defentity customer
               (orm/has-many customer_address))

;; Helper functions
(defn select-all []
  (orm/select customer))

(defn send-verification-sms [to]
  (def otp (util/random-number 4))
  ;;TODO save otp to db
  (comm/send-sms to otp))

(defn send-verification-mail [to]
  (def hash (util/random-string 64))
  ;;TODO save generated hash to db
  (comm/send-mail to hash))

(defn verify-token [token]
  nil)

;; API
(defn signup [new-customer]
  (send-verification-mail (:mail-address new-customer))
  (send-verification-sms (:mobile-number new-customer))
  (verify-token (:token new-customer))
  (orm/insert new-customer))

(defn get-profile [customer_id]
  (orm/select customer
              (orm/with customer_address)
              (orm/where {:id customer_id})))

(defn update-profile []
  nil)

;; Resources
(defresource customer-all
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (util/log-request ctx)
                          (select-all))
             :put! (fn [ctx]
                     (util/log-request ctx)
                     (signup (util/request-body ctx))))

(defresource customer-single
             :available-media-types ["application/json"]
             :allowed-methods [:get :post :put :delete]
             :handle-ok (fn [ctx]
                          (util/log-request ctx)
                          (get-profile
                            (get-in ctx [:request :params :customer_id]))))

;; Routes
(defroutes routes
           (ANY "/customer" request (customer-all request))
           (ANY "/customer/:customer_id" request (customer-single request)))
