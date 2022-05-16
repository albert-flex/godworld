import { createRouter } from "vue-router";
import { createWebHistory } from "vue-router";
import BookLibHome from "../views/book/BookLibHome.vue";
import BookContent from "../views/book/BookContent.vue";
import BookChapter from "../views/book/BookChapter.vue";
import AuthorHome from "../views/author/AuthorHome.vue";
import AuthorPage from "../views/author/AuthorPage.vue";
import AuthorNew from "../views/author/AuthorNew.vue";
import SocialHome from "../views/social/SocialHome.vue";
import SocialPage from "../views/social/SocialPage.vue";
import SocialNew from "../views/social/SocialNew.vue";
import AuthorStation from "../views/work/AuthorStation.vue";
import SocialStation from "../views/work/SocialStation.vue";
import UserLogin from "../views/user/UserLogin.vue";
import UserRegister from "../views/user/UserRegister.vue";

const routes = [
    {
        path: "/",
        name: "site",
        component: BookLibHome
    },
    {
        path: "/book",
        name: "bookHome",
        component: BookLibHome
    },
    {
        path: "/book/:id",
        name: "book",
        component: BookContent,
    },
    {
        path: "/chapter/:id",
        name: "chapter",
        component: BookChapter,
    },
    {
        path: "/author",
        name: "authorHome",
        component: AuthorHome
    },
    {
        path: "/author/new",
        name: "authorNew",
        component: AuthorNew,
    },
    {
        path: "/author/:id",
        name: "authorPage",
        component: AuthorPage,
    },
    {
        path: "/social",
        name: "socialHome",
        component: SocialHome,
    },
    {
        path: "/social/new",
        name: "socialNew",
        component: SocialNew
    },
    {
        path: "/social/:id",
        name: "socialPage",
        component: SocialPage,
    },
    {
        path: "/work/author",
        name: "authorStation",
        component: AuthorStation
    },
    {
        path: "/work/social",
        name: "socialStation",
        component: SocialStation,
    },
    {
        path:"/login",
        name:"login",
        component: UserLogin,
    },
    {
        path:"/register",
        name:"register",
        component: UserRegister,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router;