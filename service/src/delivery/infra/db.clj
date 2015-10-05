(ns delivery.infra.db
  (:require [korma.core :as orm]
            [korma.db :as db]
            [environ.core :refer [env]]))

;; (def db {:classname "com.mysql.jdbc.Driver"
;;	:subprotocol "mysql"
;;	:subname "//localhost:3306/goaamigo"
;;	:delimiters "`"
;;	:useUnicode "yes"
;;	:characterEncoding "UTF-8"
;;	:user "root"
;;	:password "root"})

(db/defdb goaamigo (db/postgres {
  :db "foodamigo"
  :user "postgres"
  :password "livefree"
  }))
