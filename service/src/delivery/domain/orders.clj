(ns delivery.domain.orders
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [noir.session :as session]
            [delivery.service.db :as db])
  (:use korma.db korma.core))
;;Functions

;; Creating list of items for the order i.e. Inserting into order items
(defn create-plan [request]
	(insert db/order_items
		(values (:order_items (:body request))))
	(update db/order_items
		(set-fields {:per_id (:person_id (:params request))})
		(where {:id (:id (:order_items (:body request)))}))
  )

;; Place an order
(defn place_order [request]
	(insert db/orders
		(values (:orders (:body request))))
	(update db/orders
		(set-fields {:id (:order_id (:params request))})
		(where (:orders (:body request))))
	(update db/order_items
		(set-fields {:o_id (:order_id (:params request))})
		(where {:per_id (:person_id (:params request))}))
	(def vls (conj (select db/person_location
			(with location)
			(modifier "DISTINCT")
			(fields [:location.id :l_id])
			(where {:person_location.pid (:id (:person (:body request))) :person_location.location_type (:place (:body request))})) (:shipment (:body request))))
	(insert db/shipment (values (conj (:ship (:body request)) vls)))
	)

;; Delete an order
(defn delete_order [request]
	(delete db/order_items 
		(where {:o_id (:order_id (:params request))}))
	(delete db/orders 
		(where {:id (:order_id (:params request))}))
  )

(defn get_order [request]
  (select orders
  	(with db/order_items
  		(with product))
  	(fields :order.id :orders.order_time :orders.expected_del_time :product.pro_name :order_items.quantity)
  	(where {:orders.id (:order_id (:params request))}))
  )

(defn get_all_orders [request]
  (select db/orders
  	(with db/order_items
  		(with db/product))
  	(fields :orders.id :orders.order_time :orders.expected_del_time :product.pro_name :order_items.quantity)
  	(where {:order_items.per_id (:person_id (:params request))})
  	)
  )
