(ns pages.info
  (:require [app.state :refer [app-state]]
            [app.utils :as u]
            [providers.api :as api]))

(defn show-vals []
  (let [supply (get-in @app-state [:calc :supply])
        mn-count (get-in @app-state [:calc :masternodes])
        mn-reward (get-in @app-state [:calc :reward])
        user-currency (get @app-state :currency)
        currency-symbol (api/cur-symbol user-currency)
        price-btc (get-in @app-state [:currencies :btc])
        price-usd (get-in @app-state [:currencies :usd])
        price-eur (get-in @app-state [:currencies :eur])
        price (u/get-user-price user-currency price-usd price-eur price-btc)
        market-cap (* supply price)
        mn-price (* 10000 price)
        mn-waiting-time (/ mn-count 60)
        mn-monthly-revenue (/ (* 43800 mn-reward) mn-count)
        roi (/ mn-monthly-revenue 10000)]
  [:div.row
    [:div.col-sm-4.col-xs-6
      [:div.panel.panel-default
        [:center.panel-heading "TransferCoin Value"]
        [:center.panel-body
          [:span "1 TX = " price " " currency-symbol]]]
      [:div.panel.panel-default
        [:center.panel-heading "Master Nodes Count"]
        [:center.panel-body
          [:span (int mn-count) " MN"]]]
      [:div.panel.panel-default
        [:center.panel-heading "Master Nodes Price"]
        [:center.panel-body
          [:span "10.000 TX = " (u/format-number mn-price) " " currency-symbol]]]]
    [:div.col-sm-4.col-xs-6
      [:div.panel.panel-default
        [:center.panel-heading "Total Coin Supply"]
        [:center.panel-body
          [:span (-> supply (int) (str) (u/kilo-numbers)) " TX"]]]
      [:div.panel.panel-default
        [:center.panel-heading "Master Nodes Reward"]
        [:center.panel-body
          [:span mn-reward " TX / minute"]]]
      [:div.panel.panel-default
        [:center.panel-heading "Expected Monthly Revenue"]
        [:center.panel-body
          [:span (u/format-number mn-monthly-revenue) " TX = " (u/format-number (* price mn-monthly-revenue)) " " currency-symbol]]]]
    [:div.col-sm-4.col-xs-6
        [:div.panel.panel-default
          [:center.panel-heading "Market Cap"]
          [:center.panel-body
            [:span (-> market-cap (int) (str) (u/kilo-numbers)) " " currency-symbol]]]
        [:div.panel.panel-default
          [:center.panel-heading "Average Waiting Time"]
          [:center.panel-body
            [:span (u/format-number mn-waiting-time) " hours"]]]
        [:div.panel.panel-default
          [:center.panel-heading "Return On Investment"]
          [:center.panel-body
            [:span (u/format-number (* 100 roi)) "%"]]]]]))

(defn component []
  [:div {:class "container"}
    [:h1 {:class "page-header"} "TransferCoin Master Nodes"]
    [show-vals]])
