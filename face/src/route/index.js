
import BookHome from "../components/site/BookHome.vue";
import UserLogin from "../components/site/UserLogin.vue";
import UserRegister from "../components/site/UserRegister.vue";
import { createRouter } from "vue-router";
import { createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        name: "site",
        component: BookHome,
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
    {
        path: "/book",
        name: "book",
        component: BookHome,
    },
];

const router= createRouter({
    history: createWebHistory(),
    routes,
});

export default router