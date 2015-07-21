(ns srv.infra.db
  (:use korma.db korma.core))

(def db {:classname "com.mysql.jdbc.Driver"
	:subprotocol "mysql"
	:subname "//localhost:3306/delivery"
	:delimiters "`"
	:useUnicode "yes"
	:characterEncoding "UTF-8"
	:user "root"
	:password "root"})

(defdb delivery db)

;; Entity
(declare customer customer_address address product product_category
         product_supplier supplier order_item order)

(defentity address)

(defentity customer
  (has-many customer_address))

(defentity customer_address
  (has-one address))

(defentity product
  (belongs-to product_category)
  (has-many product_supplier))

(defentity product_category)

(defentity product_supplier
  (has-one supplier))

(defentity supplier
  (has-one address))

(defentity order
  (has-many order_item))

(defentity order_item)


