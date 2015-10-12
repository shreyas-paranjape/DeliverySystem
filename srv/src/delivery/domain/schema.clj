(ns delivery.domain.schema
  (:require [schema.core :as clType]
            [lobos.schema :as sqlType])
  (:use [delivery.infra.util]))

(def Address
  {:struct
   {:address_string clType/Str
    :locality_id    clType/Str
    :latitude       clType/Num
    :longitude      clType/Num}
   :sql-def
   (tbl-base :address
             (sqlType/varchar :address_string 200)
             (sqlType/integer :locality_id)
             (sqlType/float :latitude)
             (sqlType/float :longitude))})

(def Comm
  {:struct
   {:comm_ref clType/Str
    :email    clType/Str
    :mobile   clType/Str}
   :sql-def
   (tbl-base :comm
             (sqlType/varchar :email 254)
             (sqlType/varchar :mobile 10))})

(def Product
  {:struct
   {:name                clType/Str
    :description         clType/Str
    :product_category_id clType/Int
    :party_id            clType/Int
    :price               clType/Num
    :preparation_time    clType/Num
    :image_url           clType/Str}
   :sql-def
   (tbl-base-name-desc :product
                       (sqlType/integer :product_category_id))})

(def Product-Category
  {:struct
   {:name        clType/Str
    :description clType/Str
    :parent_id   clType/Int}
   :sql-def
   (tbl-base-name-desc :product_category
                       (sqlType/varchar :name 100 :not-null)
                       (sqlType/integer :lft :not-null)
                       (sqlType/integer :rgt :not-null))})

(def Party
  {:struct
   {:name        clType/Str
    :description clType/Str
    :image_url   clType/Str
    :address     Address
    :comm        Comm}
   :sql-def
   (tbl-base-name-desc :party
                       (sqlType/varchar :image_url 250))})

(def Party-Comm
  {:struct
   {}
   :sql-def
   (tbl-base :party_comm
             (sqlType/integer :party_id)
             (sqlType/integer :comm_id))})

(def Party-Add
  {:struct
   {}
   :sql-def
   (tbl-base :party_add
             (sqlType/integer :party_id)
             (sqlType/integer :address_id))})

(def Order-Item
  {:struct
   {:product_id clType/Int
    :party_id   clType/Int
    :qunatity   clType/Int
    :price      clType/Num}
   :sql-def
   (tbl-base-name-desc :order_item
                       (sqlType/integer :product_category_id))})

(def Order
  {
   :struct
   {{:party_id clType/Int}
    {:order_items [Order-Item]}}
   :sql-def
   (tbl-base-name-desc :ordr
                       (sqlType/integer :product_category_id))})

(def Order-Party
  {:struct
   {}
   :sql-def
   (tbl-base :order_party
             (sqlType/integer :party_id)
             (sqlType/integer :ordr_id))})

(def Shipment)

(def Route)

(def Route-segment)

(def Deal)

;(def Delivery
;  {:sql-def
;   (sqlType/schema :delivery)})
;
;(def Schemas [Delivery])

(def Tables [Address Comm
             Party Party-Comm Party-Add
             Product Product-Category
             Order Order-Item Order-Party])
