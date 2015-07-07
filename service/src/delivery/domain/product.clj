(ns delivery.domain.product
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [clojure.java.jdbc :as j]
            [noir.session :as session]
            [delivery.service.db :as db])
  (:use korma.db korma.core))

(defn get_all_products [request]
  (if (= "restaurant" (:type (:body request)))
  (j/query db ["select product.pro_name from product_category as node, product_category as parent, product 
  		inner join product_category on product.prod_cat_id=product_category.id inner join (site inner join comm on site.comm_id=comm.id inner join location 
  				on site.location_id=location.id inner join restaurant on site.id=restaurant.id) on product.id=site.id 
  					where node.id=product.prod_cat_id and parent.name=? and 
  						node.l between parent.l and parent.r" (:category (:body request))])
  )
  (if (= "grocery" (:type (:body request)))
  (j/query db ["select product.pro_name from product_category as node, product_category as parent, product 
  		inner join product_category on product.prod_cat_id=product_category.id inner join (site inner join comm on site.comm_id=comm.id inner join location 
  				on site.location_id=location.id inner join restaurant on site.id=grocery.id) on product.id=site.id 
  					where node.id=product.prod_cat_id and parent.name=? and 
  						node.l between parent.l and parent.r" (:category (:body request))])
  )
  (if (= "pharmacy" (:type (:body request)))
  (j/query db ["select product.pro_name from product_category as node, product_category as parent, product 
  		inner join product_category on product.prod_cat_id=product_category.id inner join (site inner join comm on site.comm_id=comm.id inner join location 
  				on site.location_id=location.id inner join restaurant on site.id=pharmacy.id) on product.id=site.id 
  					where node.id=product.prod_cat_id and parent.name=? and 
  						node.l between parent.l and parent.r" (:category (:body request))])
  )
)

(defn get_specific_product [request]
	(select db/product
		(where {:site_id (:site_id (:params request)) :id (:product_id (:params request)) :prod_cat_id (subselect product_category (fields :id) (where {:name (:category (:body request))}))
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