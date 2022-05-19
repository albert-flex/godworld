import { createApp } from 'vue'
import { createPinia } from 'pinia';
import Antd from "ant-design-vue";
import App2 from './App2.vue'
import router from "./config/route";
import "ant-design-vue/dist/antd.css";

const pinia=createPinia();
const app = createApp(App2);

app.use(router);
app.use(Antd);
app.use(pinia);
app.mount("#app");
