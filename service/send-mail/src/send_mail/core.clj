(ns send-mail.core
(:require [postal.core :as p]))

(defn send-mail []
	(p/send-message {:host "smtp.gmail.com"
         		:user "xyz@gmail.com" ;; Sender's gmail email address.
         		:pass "sail2000boat" ;; Sender's password
         		:ssl :yes!!!11}
         		{:from "xyz@gmail.com" ;; Sender's gmail email address.
         		:to "abc@pqr.com" ;; receipients email address
         		:subject "Hi!"
         		:body "Test"})
)

(defn -main[& args] (send-mail))