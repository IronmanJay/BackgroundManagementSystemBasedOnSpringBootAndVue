import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 引入全局css样式
import './assets/gloable.css'
// 引入request.js
import request from "@/utils/request";
import store from './store/store'

// main.js全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

Vue.config.productionTip = false;

// 设置大小，适配屏幕
Vue.use(ElementUI, {size: "mini"});

// 引入request.js
Vue.prototype.request = request

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
