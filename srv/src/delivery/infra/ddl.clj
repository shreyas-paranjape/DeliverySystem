(ns delivery.infra.ddl
  (:require [lobos.connectivity :as ddl]
            [lobos.core :as c]
            [delivery.domain.schema :as sch]
            [delivery.infra.db :as db]))

(ddl/open-global db/spec)

(defn create-schema []
  (doseq [table sch/Tables]
    (c/drop (:sql-def table))
    (c/create (:sql-def table))))
