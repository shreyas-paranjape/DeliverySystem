(ns delivery.domain.site
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [noir.session :as session]
            [delivery.service.db :as db]
            [delivery.service.db :as db])
  (:use korma.db korma.core))

;; get all sites belonging to a certain class
(defn get-all-sites [request]
	(if (= "food" (:class (:body request)))
	(select db/restaurant
		(with db/site
			(with db/location)
			(with db/comm))
		(fields :comm.email :comm.mobile :location.address :restaurant.name)))
	(if (= "grocery" (:class (:body request)))
	(select db/grocery
		(with db/site
			(with db/location)
			(with db/comm))
		(fields :comm.email :comm.mobile :location.address :grocery.name)))
	(if (= "medics" (:class (:body request)))
	(select db/pharmacy
		(with db/site
			(with db/location)
			(with db/comm))
		(fields :comm.email :comm.mobile :location.address :pharmacy.name)))
	)
