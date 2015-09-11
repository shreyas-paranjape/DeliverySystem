(ns delivery.domain.party_spec
  (:require [speclj.core :refer :all]
            [delivery.domain.party :refer :all]))


(describe "Profile"
          (it "has many address")
          (it "has an username")
          (it "has a mobile number"))

(describe "Address"
          (it "has location string")
          (it "has latitude")
          (it "has longitude"))

(run-specs)