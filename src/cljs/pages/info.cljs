(ns pages.info
  (:require [app.state :refer [app-state]]
            [app.utils :as u]
            [providers.api :as api]))

(defn component []
  [:div {:class "container"}
    [:h1 {:class "page-header"} "Rewards calculator"]
    [:div {:class "col-sm-12"}
      [:p "lol"]]])
