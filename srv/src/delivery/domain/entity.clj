(ns delivery.domain.entity
  (:use [korma.core :refer [defentity has-many has-one belongs-to]])
  (:require [delivery.infra.db]))

(declare
  address
  ordr order_item
  party_address party
  product product_category product_supplier supplier)

(defentity address)

(defentity ordr
 (has-many order_item))

(defentity order_item
  (has-one product))

(defentity party_address
  (has-one address))

(defentity party
  (has-many party_address))

(defentity product
  (belongs-to product_category)
  (has-many product_supplier))

(defentity product_category
  (has-many product))

(defentity product_supplier
  (has-one supplier))

(defentity supplier
  (has-one address))
