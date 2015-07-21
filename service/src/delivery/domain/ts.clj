(ns delivery.domain.ts
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [noir.session :as session]
            [delivery.service.db :as db])
	(:use korma.db korma.core))

(defn getts []
	(select db/ts
		(aggregate (max :ts) :latest_timestamp))
	)