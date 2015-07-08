(ns delivery.domain.customer
	(:require [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [noir.session :as session]
            [delivery.service.db :as db]
            [ring.util.response :as re]
            [delivery.domain.hash :as h]
            [delivery.domain.send-mail :as m]
            [delivery.domain.send-sms :as mob]
            [cheshire.core :refer :all])
  (:use korma.db korma.core))

;; Functions

(defn register [request]
	(if (not (select db/user_info (where {:email (:email (:user_info (:body request))) :mobile (:mobile (:user_info (:body request)))}))) 

	;; if user not present
	((insert db/user_info
		(values (:user_info (:body request))))
	(def e (h/random-string 10))
	(def m (h/random-string 4))
	(update db/user_info
		(set-fields {:email_hash e :mobile_hash m})
		(where (:user_info (:body request))))
	(def sub "Email Verification")
	(def b (str "Thank you for signing up.\nPlease verify your email address by clicking on the link given below.\n\n\n" 
		"http://www.xyz.com/customer/email_ver/"e "=" (:email (:user_info (:body request))))
	(m/send-mail (:email (:user_info (:body request))) sub b)
	(def txt (str "Please verify your cell phone number by entering the code given below in the app.\n\n" m)
	(mob/send-sms (:mobile (:user_info (:body request))) txt)
	(generate-string {:code 100 :status "Than You For Signing Up. Please verify your email address by clicking on a link which is sent to your emil address."})) 

	;;if user present
	(generate-string {:code 101 :status "User Already Present"})
	)
)
(defn sign-in [request]
	(if (select db/user_info (where (:login_info (:body request)))) 
	;; if credentials are correct
	((session/put! :login_info (:login_info (:body request)))
	  (generate-string {:code 102 :status "success"})) 
	;; if credentials are wrong
	(generate-string {:code 103 :status "failure"}))
  )

(defn verify_email [request]
	(def parts (clojure.string/split (:hash (:params request)) #"=" ))
	(def hash_string (parts 0))
	(def email (parts 1))
	(if (select db/user_info (where {:email_hash hash_string})) 
	((update db/user_info
		(set-fields {:email_ver 1})
		(where {:email email}))
	  (generate-string {:code 104 :status "Email has been verfied"})) 
	(generate-string {:code 105 :status "Email was not verfied"})
	)

(defn verify_mobile [request]
	(def hash_string (:hash (:body request)))
	(def number (:number (:params request)))
	(if (select db/user_info (where {:mobile_hash hash_string})) 
		((update db/user_info
			(set-fields {:mobile_ver 1})
			(where {:mobile number}))
		  (generate-string {:code 106 :status "Mobile Number has been verified"})) 

		(generate-string {:code 107 :status "Mobile Number was not verified"})
		)
	)
;; Profile
(defn get-profile [request]
	(select db/person_location
		(with db/location)
		(with db/person (with db/comm))
		(where {:pid (:person_id (:params request))}))
  )

(defn update-profile [request]
	(if (:comm (:body request)) 
		(update db/comm
			(set-fields (:comm (:body request)))
			(where {:id (:id (:comm (:body request)))})))
	(if (:person (:body request))  
		(update db/person
			(set-fields (:person (:body request)))
			(where {:id (:person_id (:params request))})))
	(if (:location (:body request)) 
		(update db/location
			(set-fields (:location (:body request)))
			(where {:id (:id (:location (:body request)))})))
	(if (:person_location (:body request)) 
		(update db/person_location
			(set-fields (:person_location (:body request)))
			(where {:pid (:person_id (:params request))})))
	)

;; Pay
(defn pay []

	)

(defn confirm-delivery [request]
	(update db/shipment
		(set-fields {:shipment.status 1})
		(where {:ship_id (:delivered (:body request))}))
	)



