(ns delivery.domain.order_spec
  (:require [speclj.core :refer :all]
            [delivery.domain.product :refer :all]))

(describe "Order"
          (it "has many order items")
          (it "has price")
          (it "has preparation time")
          (it "has order status")
          (it "has payment status")
          (it "has one shipment"))

(describe "Order Item"
          (it "has one product")
          (it "has quantity")
          (it "has price"))

(describe "Shipment"
          (it "belongs to one order")
          (it "has many sites")
          (it "has start site")
          (it "has end site")
          (it "has delivery time")
          (it "has one delivery route"))