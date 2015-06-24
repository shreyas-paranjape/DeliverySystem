(ns delivery.domain.orders
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [noir.session :as session])
  (:use korma.db korma.core))

(def db{:classname "com.mysql.jdbc.Driver"
	:subprotocol "mysql"
	:subname "//localhost:3306/delivery"
	:delimiters "`"
	:useUnicode "yes"
	:characterEncoding "UTF-8"
	:user "root"
	:password "root"})

(timbre/refer-timbre)

(defdb korma-db db)

;; Entities

(defentity comm
	(pk :id))
(defentity person
	(pk :id)
	(belongs-to comm))
(defentity location
	(pk :id))
(defentity person_location
	(pk :id)
	 (belongs-to location)
	  (belongs-to person {:fk :pid}))
(defentity orders
	(pk :id))
(defentity site
	(pk :id)
	(belongs-to comm)
	(belongs-to location))
(defentity product_category
	(pk :id))
(defentity product
	(pk :id)
	(belongs-to site)
	(belongs-to product_category {:fk :prod_cat}))
(defentity order_items
	(belongs-to orders {:fk :o_id})
	(belongs-to product {:fk :pro_id})
	(belongs-to person {:fk :per_id}))
(defentity shipment
	(pk :ship_id))
(defentity user_info
	(pk :id)
	(belongs-to person {:fk :id}))

;;Functions

;; Creating list of items for the order i.e. Inserting into order items
(defn create-plan [request]
	(insert order_items
		(values (:order_items request)))
  )

;; Place an order
(defn place_order [request]
	(insert orders
		(values (:orders request)))
	(update order_items
		(set-fields {:o_id (:id (:orders request))})
		(where {:per_id (:id (:person request))}))
	(def vls (conj (select person_location
			(with location)
			(modifier "DISTINCT")
			(fields [:location.id :l_id])
			(where {:person_location.pid (:id (:person request)) :person_location.location_type (:place request)})) (:shipment request)))
	(insert shipment (values (conj (:ship request) vls)))
	)

;; Delete an order
(defn delete_order [request]
	(delete order_items 
		(where {:o_id (:id (:orders request))}))
	(delete orders 
		(where {:id (:id (:orders request))}))
  )

(defn get_order [request]
  (select orders
  	(with order_items
  		(with product))
  	(fields :order.id :orders.order_time :orders.expected_del_time :product.pro_name :order_items.quantity)
  	(where {:orders.id (:id (:orders request))}))
  )

(defn get_all_orders []
  (select orders
  	(with order_items
  		(with product))
  	(fields :orders.id :orders.order_time :orders.expected_del_time :product.pro_name :order_items.quantity)
  	)
  )
