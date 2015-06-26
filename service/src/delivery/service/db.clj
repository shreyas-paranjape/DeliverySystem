(ns delivery.service.db
  (:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [korma.db :as d :refer [defdb]]
            [korma.core :as c :refer [defentity has-many
                                      has-one with select]]))

(def db {:classname "com.mysql.jdbc.Driver"
	:subprotocol "mysql"
	:subname "//localhost:3306/delivery"
	:delimiters "`"
	:useUnicode "yes"
	:characterEncoding "UTF-8"
	:user "root"
	:password "livefree"} )

(timbre/refer-timbre)

(defdb korma-db db)

;; Entities

(declare customer customer_address address vendor
         product product_category product_supplier supplier
         order_item order shipment vehicle)

(defentity customer
  (has-many customer_address))

(defentity customer_address
  (has-one address))

(defentity address)

(defentity vendor
  (has-one address))

(defentity product
  (has-one product_category)
  (has-many product_supplier))

(defentity product_category)

(defentity product_supplier
  (has-one supplier))


(defentity order
  (has-many order_item)
  (has-one shipment))

(defentity order_item
  (has-one product))

(defentity shipment
  )




;; CRUD
