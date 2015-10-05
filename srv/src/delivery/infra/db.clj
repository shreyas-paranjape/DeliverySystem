(ns delivery.infra.db
  (:require [korma.core :as core]
            [korma.db :as db]
            [environ.core :refer [env]]))

(def db {:classname         "com.mysql.jdbc.Driver"
         :subprotocol       "mysql"
         :subname           "//localhost:3306/delivery"
         :delimiters        "`"
         :useUnicode        "yes"
         :characterEncoding "UTF-8"
         :user              "root"
         :password          "root"})

(db/defdb delivery db)
