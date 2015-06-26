(ns delivery.domain.customer
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
	(pk :id)
	)
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
	(belongs-to person {:fk :per_id})
	)
(defentity shipment
	(pk :ship_id))
(defentity user_info
	(pk :id)
	(belongs-to person {:fk :id}))

;; Functions

(defn register [request]

	
	)
(defn sign-in []
	
  )


;; Profile
(defn get-profile [request]
	(select person_location
		(with location)
		(with person (with comm))
		(where {:pid (:person_id (:params request))}))
  )

(defn update-profile [request]
	(if (:comm (:body request)) 
		(update comm
			(set-fields (:comm (:body request)))
			(where {:id (:id (:comm (:body request)))})))
	(if (:person (:body request))  
		(update person
			(set-fields (:person (:body request)))
			(where {:id (:person_id (:params request))})))
	(if (:location (:body request)) 
		(update location
			(set-fields (:location (:body request)))
			(where {:id (:id (:location (:body request)))})))
	(if (:person_location (:body request)) 
		(update person_location
			(set-fields (:person_location (:body request)))
			(where {:pid (:person_id (:params request))})))
	)

;; Pay
(defn pay []

	)

(defn confirm-delivery [request]
	(update shipment
		(set-fields {:shipment.status 1})
		(where {:ship_id (:delivered (:body request))}))
	)



