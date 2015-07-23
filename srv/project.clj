(defproject srv "0.1.0-SNAPSHOT"
  :description "Intra city package delivery system"
  :url "http://tbd.com/delivery"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 ;; Environment variables
                 [environ "1.0.0"]
                 ;; Logging, profiling
                 [com.taoensso/timbre "4.0.2"]
                 ;; DB JDBC driver
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [mysql/mysql-connector-java "5.1.25"]
                 ;; HTTP routing
                 [compojure "1.3.2"]
                 ;; HTTP abstraction
                 [ring/ring-defaults "0.1.4"]
                 [ring/ring-json "0.3.1"]
                 ;; Auth
                 [com.cemerick/friend "0.2.1"]
                 [friend-oauth2 "0.1.3"]
                 ;; REST
                 [liberator "0.13"]
                 ;; ORM
                 [korma "0.4.2"]
                 ;; Mail
                 [clojurewerkz/mailer "1.2.0"]
                 ;; SMS
                 [twilio-api "1.0.0"]
                 ;; Http Client
                 [http-kit "2.1.19"]]
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]]
  :ring {:handler srv.infra.web/app}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
