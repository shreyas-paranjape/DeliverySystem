(ns delivery.domain.shipment_spec
  (:require [speclj.core :refer :all]
            [delivery.domain.product :refer :all]))

(describe "Shipment"
  (it "has many shipment items")
  (it "has start time")
  (it "has end time")
  (it "has many sites")
  (it "has status")
  (it "has delivery plan"))

(describe "Delivery plan"
  (it "has route")
  (it "has vehicle")
  (it "has delivery boy"))

(describe "Route"
  (it "has many sites"))  
