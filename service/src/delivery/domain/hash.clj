(ns delivery.domain.hash)


(def random (java.util.Random.))
(def characs 
   	(map char (concat (range 48 58) (range 66 92) (range 97 123))))
(defn random-char [] 
  	(nth characs (.nextInt random (count chars))))
(defn random-string [length]
  	(apply str (take length (repeatedly random-char))))