(ns delivery.middleware.keywordize
  (:require [clojure.walk :as walk]))

;; Handler
(defn keywordize-params
  [handler]
  (fn [request]
    (let [req (assoc-in request [:params] (walk/keywordize-keys (:params request)))]
      (handler req))))
