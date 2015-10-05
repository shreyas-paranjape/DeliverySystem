(ns delivery.domain.shipment
  (:use delivery.domain.entity)
  (:require [korma.core :as orm]
            [liberator.core :refer [defresource]]
            [compojure.core :refer [ANY defroutes]]
            [taoensso.timbre :as timbre]))
            
(timbre/refer-timbre)
(timbre/set-level! :debug)
