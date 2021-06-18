
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/orderManager"

import DeliveryManager from "./components/deliveryManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/order',
                name: 'orderManager',
                component: orderManager
            },

            {
                path: '/delivery',
                name: 'deliveryManager',
                component: deliveryManager
            },



    ]
})
