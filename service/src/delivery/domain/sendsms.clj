(ns delivery.domain.sendsms 
(:require [clj-http.client :as client]))

(defn send-sms [rn text]
	(client/post "http://api.mVaayoo.com/mvaayooapi/MessageCompose?"
		{:form-params 
			{:user "asdas:asds" ;; User credentials ":" separated ID and password string.
			  :senderID "xyz";;"TEST SMS" ;; SenderID - Needs to be registered and bought ex: GoToGoa
			  :receipientno rn ;; Can be comma separated inside the string if you want to send it to multiple phone numbers.
			  :dcs 0 ;; Data Coding Scheme
			  :msgtxt text ;; The body of the text message.
			  :state 4} ;; Mandatory
		}))