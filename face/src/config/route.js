import { createRouter } from "vue-router";
import { createWebHistory } from "vue-router";
import BookLibHome from "../views/book/BookLibHome.vue";
import BookContent from "../views/book/BookContent.vue";
import BookChapter from "../views/book/BookChapter.vue";
import AuthorHome from "../views/author/AuthorHome.vue";

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
        path:"/author/home",
        name:"authorHome",
        component: AuthorHome
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router;