(ns delivery.domain.send-mail
(:require [postal.core :as p]))

(defn send-mail [rm sub bod]
	(p/send-message {:host "smtp.gmail.com"
         		:user "abc@gmail.com";; Sender's gmail email address.
         		:pass "password";; Sender's password
         		:ssl :yes!!!11}
         		{:from "abc@gmail.com" ;; Sender's gmail email address.
         		:to rm ;; receipients email address
         		:subject sub
         		:body bod})
)