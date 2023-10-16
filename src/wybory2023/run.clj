(ns wybory2023.run
  (:require [clojure.java.io :as io]
            [clojure.java.shell :as shell]
            [hiccup2.core :refer [html]]
            [wybory2023.dhondt :as dhondt]
            [wybory2023.parse :as parse]))

(defn try-download! []
  (with-open [in (io/input-stream "https://wybory.gov.pl/sejmsenat2023/data/obkw/pl_po_okr_sejm.blob")
              out (io/output-stream "pl_po_okr_sejm.blob")]
    (io/copy in out)))

(def retry-wait 10000)

(defn download! []
  (loop [retries 60]
    (let [result (try
                   (try-download!)
                   (java.util.Date.)
                   (catch Exception e :retry))]
      (if (= result :retry)
        (when (pos? retries)
          (println "Retries left:" retries)
          (Thread/sleep retry-wait)
          (recur (dec retries)))
        result))))

(defn convert! []
  (shell/sh "node" "js/index.js" "pl_po_okr_sejm.blob"))

(defn layout [& content]
  (str
   "<!DOCTYPE html>\n"
   (html
    [:html
     [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:link {:rel "stylesheet" :href "https://cdn.jsdelivr.net/npm/uikit@3.16.22/dist/css/uikit.min.css"}]
      [:script {:src "https://cdn.jsdelivr.net/npm/uikit@3.16.23/dist/js/uikit.min.js"}]]
     (into
      [:body]
      content)])))

(defn get-results []
  (let [res (parse/parse-json "pl_po_okr_sejm.json")
        parsed (parse/parse-results res)
        regions (dhondt/calculate parsed)
        total (dhondt/total regions)]
    {:parsed parsed, :regions regions, :total total}))

(defn format-date [d]
  (let [sdf (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss")]
    (.setTimeZone sdf (java.util.TimeZone/getTimeZone "Europe/Warsaw"))
    (.format sdf d)))

(defn committee-table [seats]
  [:table
   [:tr [:th "Komitet"] [:th "Mandaty"]]
   (for [[k v] (sort-by second > seats)]
     [:tr [:td k] [:td v]])])

(defn generate [time {:keys [total regions] :as results}]
  (let [ts (format-date time)
        content
        (layout
         [:div.uk-section
          [:div.uk-container
           [:h1 "Podział na mandaty na podstawie cząstkowych wyników PKW"]
           [:p [:i "Wygenerowany nieoficjalnym skryptem o godzinie: " ts]]]]
         [:div.uk-section
          [:div.uk-container
           [:h2 "Mandaty w skali kraju"]
           (committee-table total)]]
         [:div.uk-container
          [:h2 "Mandaty w okręgach"]]
         (for [{:keys [region seats]} regions]
           [:div.uk-section
            [:div.uk-container
             [:h3 "Okręg " region]
             (committee-table seats)]])
         [:hr]
         [:div.uk-section
          [:div.uk-container
           [:p
            "Stronę machnął: " [:a {:href "https://danieljanus.pl"} "Daniel Janus"] " | "
            [:a {:href "https://github.com/nathell/wybory2023"} "Kod źródłowy"] " | "
            [:a {:href "/wybory2023/archive"} "Poprzednie wyniki"]]
           [:p "Kod pisany na kolanie na podstawie reverse-engineeringu danych z PKW. Podchodź do wyników z dystansem."]]])]
    (spit "index.html" content)
    (spit (str "archive/index-" ts ".html") content)
    (spit (str "archive/raw/" ts ".edn") (pr-str results))))

(defn run [_opts]
  (let [time (download!)
        _ (convert!)
        results (get-results)]
    (generate time results)))
