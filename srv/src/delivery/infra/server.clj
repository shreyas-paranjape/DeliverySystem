(ns delivery.infra.server
  (:gen-class)
  (:require [clojure.tools.cli :as cli]
            [org.httpkit.server :as srv]
            [delivery.infra.web :as web]
            [taoensso.timbre :as timbre]))

(timbre/refer-timbre)
(timbre/set-level! :debug)

(defn- to-int [s] (Integer/parseInt s))

(defonce server (atom nil))
(defonce app-configs (atom {:profile :dev}))

(defn cfg [key & [default]]
  (if-let [v (or (key @app-configs) default)]
    v
    (when-not (contains? @app-configs key)
      (throw (RuntimeException. (str "unknow config for key " (name key)))))))

(defn start-server []
  (when-not (nil? @server) (@server))
  (reset! server
          (srv/run-server
            web/app
            {:port (cfg :port) :thread (cfg :thread)})))

(defn -main [& args]
  (let [[options _ banner]
        (cli/cli args
                 ["-p" "--port" "Port to listen" :default 3000 :parse-fn to-int]
                 ["--thread" "Http worker thread count" :default 4 :parse-fn to-int]
                 ["--profile" "dev or prod" :default :dev :parse-fn keyword]
                 ["--[no-]help" "Print this help"])]
    (when (:help options) (println banner) (System/exit 0))
    (swap! app-configs merge options)
    (start-server)
    (info (str "server started. listen on 0.0.0.0@" (cfg :port)))))
