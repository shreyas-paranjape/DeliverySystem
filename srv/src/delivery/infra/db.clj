(ns delivery.infra.db
  (:require
    [korma.db :as db]
    [environ.core :refer [env]]
    [clojure.data.json :as json])
  (:import (java.sql Timestamp)))

(def spec {:classname         "com.mysql.jdbc.Driver"
           :subprotocol       "mysql"
           :subname           "//localhost:3306/delivery"
           :delimiters        "`"
           :useUnicode        "yes"
           :characterEncoding "UTF-8"
           :user              "root"
           :password          "root"})

(extend-type Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(db/defdb delivery spec)
