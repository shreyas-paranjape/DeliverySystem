(ns delivery.domain.entity
  (:use [korma.core :refer [defentity has-many has-one belongs-to]]
        [delivery.infra.db]))

(declare
  address
  comm
  ordr
  order_item
  order_item_status_type
  order_party
  order_party_role_type
  order_status_type
  party
  party_address
  party_comm
  party_role
  party_site
  price_component_type
  product
  product_category
  product_party
  product_party_role_type
  product_price_component
  route
  route_segment
  shipment
  shipment_order
  site
  )

(defentity address
           (has-one party_address)
           (has-one site))

(defentity comm
           (has-one party_comm)
           (has-one site))

(defentity ordr
           (has-many shipment_order)
           (has-many order_party)
           (has-many order_item)
           (belongs-to order_status_type))

(defentity order_item
           (belongs-to product)
           (belongs-to order_item_status_type)
           (belongs-to ordr))

(defentity order_item_status_type
           (has-many order_item))

(defentity order_party
           (belongs-to party)
           (belongs-to ordr))

(defentity order_status_type
           (has-many ordr))

(defentity party
           (has-many party_role)
           (has-many order_party)
           (has-many party_comm)
           (has-many party_address)
           (has-many product_party)
           (has-many party_site))

(defentity party_address
           (belongs-to address)
           (belongs-to party))

(defentity party_comm
           (belongs-to party)
           (belongs-to comm))

(defentity party_role
           (belongs-to party))

(defentity party_site
           (belongs-to party)
           (belongs-to site))

(defentity price_component_type
           (has-many product_price_component))

(defentity product
           (has-many product_party)
           (has-many order_item)
           (has-many product_price_component)
           (belongs-to product_category))

(defentity product_category
           (has-many product))

(defentity product_party
           (belongs-to party)
           (belongs-to product)
           (belongs-to product_party_role_type))

(defentity product_party_role_type
           (has-many product_party))

(defentity product_price_component
           (belongs-to product)
           (belongs-to price_component_type))

(defentity route
           (has-one shipment)
           (has-many route_segment))

(defentity route_segment
           (belongs-to route)
           (belongs-to site {:fk :site_one_id})
           (belongs-to site {:fk :site_two_id}))

(defentity shipment
           (has-many shipment_order)
           (belongs-to route))

(defentity shipment_order
           (belongs-to shipment)
           (belongs-to ordr))

(defentity site
           (has-many route_segment {:fk :site_one_id})
           (has-many route_segment {:fk :site_two_id})
           (has-many party_site)
           (belongs-to comm)
           (belongs-to address))