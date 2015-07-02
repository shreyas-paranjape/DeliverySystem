(ns delivery.domain.customer
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [noir.session :as session]
            [delivery.service.db :as db])
  (:use korma.db korma.core))

;; Functions

(defn register [request]

	
	)
(defn sign-in []
	
  )


;; Profile
(defn get-profile [request]
	(select db/person_location
		(with db/location)
		(with db/person (with db/comm))
		(where {:pid (:person_id (:params request))}))
  )

(defn update-profile [request]
	(if (:comm (:body request)) 
		(update db/comm
			(set-fields (:comm (:body request)))
			(where {:id (:id (:comm (:body request)))})))
	(if (:person (:body request))  
		(update db/person
			(set-fields (:person (:body request)))
			(where {:id (:person_id (:params request))})))
	(if (:location (:body request)) 
		(update db/location
			(set-fields (:location (:body request)))
			(where {:id (:id (:location (:body request)))})))
	(if (:person_location (:body request)) 
		(update db/person_location
			(set-fields (:person_location (:body request)))
			(where {:pid (:person_id (:params request))})))
	)

;; Pay
(defn pay []

	)

(defn confirm-delivery [request]
	(update db/shipment
		(set-fields {:shipment.status 1})
		(where {:ship_id (:delivered (:body request))}))
	)



