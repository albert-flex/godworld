
import SiteHome from "../components/site/SiteHome.vue";
import UserLogin from "../components/site/UserLogin.vue";
import UserRegister from "../components/site/UserRegister.vue";
import { createRouter } from "vue-router";
import { createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        name: "site",
        component: SiteHome,
    },
    {
        path: "/login",
        name: "login",
        component: UserLogin,
    },
    {
        path:"/register",
        name: "register",
        component: UserRegister,
    },
];

const router= createRouter({
    history: createWebHistory(),
    routes,
});

export default router