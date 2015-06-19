(ns delivery-service.domain.delivery
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

(defentity order
	(pk :id)
	(belongs-to person {:fk :per_id}))
(defentity order_items
	(belongs-to oder {:fk :o_id})
	(belongs-to product {:fk :pro_id}))
(defentity site
	(pk :id)
	(belongs-to comm)
	(belongs-to location))
(defentity product_category
	(pk :id)
	(belongs-to product_category {:fk :parent_id}))
(defentity product
	(pk :id)
	(belongs-to site)
	(belongs-to product_category {:fk :prod_cat}))
(defentity shipment
	(pk :ship_id))
(defentity user_info
	(pk :id)
	(belongs-to person {:fk :id}))

;;Functions

(defn create-plan [request]
	(insert order_items
		(values (:order_items request)))
	(insert order
		(values (:order request)))
	(update order_items
		(set-fields (:o_id (:id (:order request))))
		(where {:id (subselect order_items
                                    (aggregate
                                     (max :id)))}))
  )

(defn delete [delivery]

  nil)

(defn update [order delivery]
  nil)

(defn assign [delivery delivery-boy]
  nil)
