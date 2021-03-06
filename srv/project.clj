(defproject srv "0.1.0-SNAPSHOT"
  :description "Intra city package delivery system"
  :url "http://tbd.com/delivery"
  :min-lein-version "2.0.0"
  ;:test-paths ["spec"]
  :main delivery.infra.server
  ;; :aot [delivery.infra.server]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.nrepl "0.2.10"]
                 ;; Environment variables
                 [environ "1.0.0"]
                 ;; Logging, profiling
                 [com.taoensso/timbre "4.1.1"]
                 ;; Util
                 [org.clojure/tools.cli "0.3.3"]
                 [org.clojure/core.match "0.3.0-alpha4" :exclusions [org.clojure/core.cache]]
                 [org.clojure/core.cache "0.6.4"]
                 ;; DB JDBC driver
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [mysql/mysql-connector-java "5.1.36"]
                 ;; DB schema creator
                 [lobos "1.0.0-beta3"]
                 ;; HTTP routing
                 [compojure "1.4.0"]
                 ;; HTTP abstraction
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-devel "1.4.0"]
                 ;; Auth
                 [com.cemerick/friend "0.2.1"]
                 ;[friend-oauth2 "0.1.3"]
                 ;; REST
                 [liberator "0.13"]
                 ;; JSON Parsing
                 ;;[cheshire "5.4.0"]]
                 ;; ORM
                 [korma "0.4.2"]
                 ;; Mail
                 [clojurewerkz/mailer "1.2.0"]
                 ;; Schema
                 [prismatic/schema "1.0.1"]
                 ;; Http
                 [http-kit "2.1.19"]]

  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.0.0"]
            [speclj "3.3.0"]]
  :ring {:handler delivery.infra.web/app}
  :profiles
  {:uberjar {:aot :all}
   :production
            {:ring
             {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev     {:dependencies [[javax.servlet/servlet-api "2.5"]
                            [ring-mock "0.1.5"]
                            [speclj "3.3.0"]]}})
