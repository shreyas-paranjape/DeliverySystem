(ns sms-mvaayoo.core 
(:require [clj-http.client :as client]))

(defn send-sms []
	(client/post "http://api.mVaayoo.com/mvaayooapi/MessageCompose?"
		{:form-params 
			{:user "naik.punit44@gmail.com:sail2boat" ;; User credentials ":" separated ID and password.
			  :senderID "TEST SMS" ;; SenderID - Needs to be registered and bought ex: GoToGoa
			  :receipientno "8275298308"
			  :dcs 0 ;; Data Coding Scheme
			  :msgtxt "This is Test message" ;; The body of the text message.
			  :state 4} ;; Mandatory
		}))

(defn -main [] (send-sms))
