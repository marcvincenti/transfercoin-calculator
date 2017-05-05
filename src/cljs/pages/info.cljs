(ns pages.info
  (:require [app.state :refer [app-state]]
            [app.utils :as u]
            [providers.api :as api]))

(defn show-vals []
  [:div.row
    [:div.col-sm-4.col-xs-6
        [:div.panel.panel-default
          [:center.panel-heading "Total Coin Supply"]
          [:center.panel-body
            [:span (-> (get-in @app-state [:calc :supply])
                      (int) (str) (u/kilo-numbers)) " TX"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Master Nodes Count"]
          [:center.panel-body
            [:span (int (get-in @app-state [:calc :masternodes])) " MN"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Block Time"]
          [:center.panel-body
            [:span "60 sec."]]]]
    [:div.col-sm-4.col-xs-6
      [:div.panel.panel-default
        [:center.panel-heading "TransferCoin Value"]
        [:center.panel-body
          [:span "1 TX"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Master Nodes Price"]
          [:center.panel-body
            [:span "10.000 TX"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Master Nodes Reward"]
          [:center.panel-body
            [:span (get-in @app-state [:calc :reward]) " TX"]]]]
    [:div.col-sm-4.col-xs-6
        [:div.panel.panel-default
          [:center.panel-heading "Market Cap"]
          [:center.panel-body
            [:span (int (get-in @app-state [:calc :masternodes])) " MN"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Master Nodes Reward"]
          [:center.panel-body
            [:span (get-in @app-state [:calc :reward]) " TX"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Master Nodes Reward"]
          [:center.panel-body
            [:span (get-in @app-state [:calc :reward]) " TX"]]]]])

(defn component []
  [:div {:class "container"}
    [:h1 {:class "page-header"} "TransferCoin Masternodes"]
    [show-vals]])
