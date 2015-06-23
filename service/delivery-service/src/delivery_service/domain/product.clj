(ns delivery-service.domain.product
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
	(pk :id))
(defentity order_items
	(belongs-to order {:fk :o_id})
	(belongs-to product {:fk :pro_id})
	(belongs-to person {:fk :per_id}))
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


(defn get_all_products [request]
  (select product
  	(where {:site_id (:site_id (:product request))}))
  )

(defn get_specific_product [request]
	(select product
		(where {:site_id (:site_id (:product request)) :id (:id (:product request))}))
	)

(defn search [criteria]
	(j/query db ["select * from product where price between ? and ? or user_rating >= ?" (:low (:price_range criteria)) (:high (:price_range criteria)) (:user_rating criteria)])
  )

(defn add [request]
  (insert product
  	(values (:product request)))
  )

(defn update [request]
  (update product
  	(set-fields (:product request)
  	(where {:id (:id (:product request)})))

(defn delete [request]
	(delete product
		where {:id (:id (:product request))})
	)