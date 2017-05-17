(ns pages.staking
  (:require [reagent.core :as r]
            [app.state :refer [app-state]]
            [app.utils :as u]
            [providers.api :as api]))

(def blocks-per-day 1440)
(def blocks-per-month (* blocks-per-day 30.4368499))

(defn form [staking-tx percent-staked]
  [:div.row
    [:div.col-md-6
      [:div.input-group
        [:span.input-group-addon "TX Stacked"]
        [:input.form-control {:type "number" :value @staking-tx
          :on-change #(reset! staking-tx (-> % .-target .-value))}]]]
    [:div.col-md-6
      [:div.input-group
        [:span.input-group-addon "Percent of stacked tx"]
        [:input.form-control {:type "number" :value @percent-staked
          :on-change #(reset! percent-staked (-> % .-target .-value))}]]]])

(defn staking [staking-tx percent-staked]
  (let [reward 0.5
        supply (get-in @app-state [:calc :supply])
        mn-count (get-in @app-state [:calc :masternodes])
        max-staking-supply (- supply (* 10000 mn-count))
        estimated-staking-supply (* max-staking-supply (/ @percent-staked 100))
        staking-waiting-time (/ estimated-staking-supply (* 60 @staking-tx))
        staking-monthly-revenue (/ (* @staking-tx blocks-per-month reward) estimated-staking-supply)]
    [:div.row
      [:ul.list-group
        [:li.list-group-item [:b "Max Staking Supply: "]
          (-> max-staking-supply (int) (str) (u/kilo-numbers))]
        [:li.list-group-item [:b "Estimated Staking Supply: "]
          (-> estimated-staking-supply (int) (str) (u/kilo-numbers))]
        [:li.list-group-item [:b "Reward: "]
          reward " TX"]
        [:li.list-group-item [:b "Waiting Time: "]
          (u/format-number staking-waiting-time) " hours"]
        [:li.list-group-item [:b "Estimated Monthly Revenue: "]
          (u/format-number staking-monthly-revenue) " TX"]]]))

(defn component []
  (let [staking-tx (r/atom 10000)
        percent-staked (r/atom 40)]
    [:div {:class "container"}
      [:h1 {:class "page-header"} "TransferCoin Staking Calculator"]
      [form staking-tx percent-staked]
      [:br]
      [staking staking-tx percent-staked]]))
