(ns delivery.domain.product
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [noir.session :as session]
            [delivery.service.db :as db]
            [delivery.service.db :as db])
  (:use korma.db korma.core))

(defn get_all_products [request]
  (select db/product
  	(where {:site_id (:site_id (:params request))}))
  )

(defn get_specific_product [request]
	(select db/product
		(where {:site_id (:site_id (:params request)) :id (:product_id (:params request))}))
	)

(defn search_p [criteria]
	(j/query db ["select * from product where price between ? and ? or user_rating >= ?" (:low (:price_range criteria)) (:high (:price_range criteria)) (:user_rating criteria)])
  )

(defn add_p [request]
  (insert db/product
  	(values (:product (:body request))))
  (update db/product
  	(set-fields {:site_id (:site_id (:params request)) :id (:product_id (:params request))})
  	(where (:product (:body request))))
  )

(defn update_p [request]
  (update db/product
  	(set-fields (:product (:body request)))
  	(where {:id (:product_id (:params request))})))

(defn delete_p [request]
	(delete db/product
		(where {:id (:product_id (:params request))}))
	)