(ns components.menu-bar
  (:require [app.state :refer [app-state]]
            [providers.api :as api]))

(defn component []
  (let [active? (fn [p] (when (= p (:page @app-state)) {:class "active"}))]
    [:nav {:class "navbar navbar-default navbar-fixed-top"}
      [:div {:class "container"}
        [:div {:class "navbar-header"}
          [:button {:type "button" :class "navbar-toggle collapsed"
                    :data-toggle "collapse" :aria-expanded "false"
                    :data-target "#bs-example-navbar-collapse-1"}
            [:span {:class "icon-bar"}]
            [:span {:class "icon-bar"}]
            [:span {:class "icon-bar"}]]
          [:a {:class "navbar-brand" :href "#/"}
            [:img {:id "logo-tx" :src "assets/TX.png" :alt "TransferCoin logo"}]]]

  [:div {:class "collapse navbar-collapse" :id "bs-example-navbar-collapse-1"}

    [:ul {:class "nav navbar-nav"}
      [:li (active? :info) [:a {:href "#/"} "Masternodes"]]
      [:li (active? :staking) [:a {:href "#/staking"} "Staking"]]]

    [:form.navbar-form.navbar-right
        [:select.form-control {:value (get @app-state :currency)
          :on-change #(swap! app-state assoc-in [:currency]
                        (-> % .-target .-value))}
          (for [c api/cur-available] ^{:key c} [:option c])]]]]]))
