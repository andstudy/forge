(ns backup-moniwiki.core
  (:use clojure.java.io))

(defn download-page [pname]
  (with-open [wrtr (writer 
                     (format "downloads/%s.txt" 
                             (clojure.string/replace pname #"/" "_")))]
    (.write wrtr 
            (slurp 
              (format "http://www.andstudy.com/andwiki/wiki.php/%s?action=raw" 
                      (clojure.string/replace pname #" " "%20"))))))

(with-open [rdr (reader "resources/_index.txt")]
  (map #(download-page %) (doall (line-seq rdr))))

