import { createRouter } from "vue-router";
import { createWebHistory } from "vue-router";
import BookLibHome from "../views/book/BookLibHome.vue";
import BookContent from "../views/book/BookContent.vue";
import BookChapter from "../views/book/BookChapter.vue";
import AuthorHome from "../views/author/AuthorHome.vue";
import AuthorPage from "../views/author/AuthorPage.vue";
import AuthorNew from "../views/author/AuthorNew.vue";
import SocialHome from "../views/social/SocialHome.vue";

const routes = [
    {
        path: "/",
        name: "site",
        component: BookLibHome
    },
    {
        path:"/book/:id",
        name:"book",
        component: BookContent,
    },
    {
        path:"/chapter/:id",
        name:"chapter",
        component: BookChapter,
    },
    {
        path:"/author",
        name:"authorHome",
        component: AuthorHome
    },
    {
        path: "/author/new",
        name:"authorNew",
        component: AuthorNew,
    },
    {
        path:"/author/:id",
        name:"authorPage",
        component: AuthorPage,
    },
    {
        path:"/social",
        name:"socialHome",
        component: SocialHome,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router;