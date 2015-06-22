(ns delivery-service.domain.customer
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
	)
(defentity order_items
	(belongs-to order {:fk :o_id})
	(belongs-to product {:fk :pro_id})
	(belongs-to person {:fk :per_id})
	)
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
		(where {:pid (:id request)}))
  nil)

(defn update-profile [request]
	(if (:comm request) 
		(update comm
			(set-fields (:comm request))
			(where {:id (:id (:comm request))})))
	(if (:person request) 
		(update person
			(set-fields (:person request))
			(where {:id (:id (:person request))})))
	(if (:location request) 
		(update person
			(set-fields (:location request))
			(where {:id (:id (:location request))})))
	(if (:person_location request) 
		(update person
			(set-fields (:person_location request))
			(where {:id (:id (:person_location request))})))
	)

;; Pay
(defn pay []

	)

;; Order
(defn place-order [request]
	(transaction
		(insert order (values (:order request)))
		(insert order_items (values (:order_items request)))
		(def vals (conj (select person_location
			(with location)
			(modifier "DISTINCT")
			(fields [:location.id :l_id])
			(where {:person_location.pid (:per_id (:order request)) :person_location.location_type (:place request)})) (:shipment request)))
		(insert shipment (values vals))
	)
)

(defn confirm-delivery [request]
	(update shipment
		(where {:ship_id (:delivered request)})
		(set-fields (:shipment.status 1)))
	)



