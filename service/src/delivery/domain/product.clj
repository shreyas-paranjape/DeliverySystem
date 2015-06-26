(ns delivery.domain.product
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
	(belongs-to site))
(defentity order_items
	(belongs-to orders {:fk :o_id})
	(belongs-to product {:fk :pro_id})
	(belongs-to person {:fk :per_id}))
(defentity shipment
	(pk :ship_id))
(defentity user_info
	(pk :id)
	(belongs-to person {:fk :id}))


(defn get_all_products [request]
  (select product
  	(where {:site_id (:site_id (:params request))}))
  )

(defn get_specific_product [request]
	(select product
		(where {:site_id (:site_id (:params request)) :id (:product_id (:params request))}))
	)

(defn search_p [criteria]
	(j/query db ["select * from product where price between ? and ? or user_rating >= ?" (:low (:price_range criteria)) (:high (:price_range criteria)) (:user_rating criteria)])
  )

(defn add_p [request]
  (insert product
  	(values (:product (:body request))))
  (update product
  	(set-fields {:site_id (:site_id (:params request)) :id (:product_id (:params request))})
  	(where (:product (:body request))))
  )

(defn update_p [request]
  (update product
  	(set-fields (:product (:body request)))
  	(where {:id (:product_id (:params request))})))

(defn delete_p [request]
	(delete product
		(where {:id (:product_id (:params request))}))
	)