import * as Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from 'axios';

const app = Vue.createApp(App);

app.use(store);
app.use(router);
app.use(ElementPlus);
app.mount('#app');