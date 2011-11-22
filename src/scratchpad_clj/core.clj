(ns scratchpad-clj.core
  (:require [com.twinql.clojure.http :as http]
            [net.cgrand.enlive-html :as html]
            [net.cgrand.xml :as xml] 
            [com.ashafa.clutch :as couchdb]
            [clj-redis.client :as redis]
            [com.mefesto.wabbitmq :as wabbitmq]
            [ring.adapter.jetty :as jetty])
  (:use [clojure.core.match :only [match]]
        [incanter core charts]
        [cheshire.core]))

(defn str->reader [s]
  (java.io.StringReader. s))

(defn url->text [url]
  (-> url java.net.URI. (http/get :as :string) :content))

(defn url->json [url]
  (parse-string (url->text url)))

(defn page-text->html-resource [text]
  (html/html-resource (str->reader text)))

(defn url->html [url]
  (first (page-text->html-resource (url->text url))))
