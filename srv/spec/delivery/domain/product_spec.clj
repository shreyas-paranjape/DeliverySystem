(ns delivery.domain.product-spec
  (:require [speclj.core :refer :all]
            [delivery.domain.product :refer :all]))

(describe "Product"
          (it "has identification")
          (it "has title")
          (it "has description")
          (it "has preparation time")
          (it "has price")
          (it "has image")
          (it "has one supplier")
          (it "has inventory. ( Number of simultaneous orders to take )"))

(describe "Supplier"
          (it "has title")
          (it "has logo"))

(run-specs)