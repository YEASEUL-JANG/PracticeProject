import { createRouter, createWebHistory } from 'vue-router';
import Home from '../pages/index.vue';
import Todos from '../pages/todos/index.vue';
import Practice from '../pages/practice/test2_mine.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path:'/',
            name: 'Home',
            component: Home
        },
        {
            path:'/todos',
            name: 'Todos',
            component: Todos
        },
        {
            path:'/practice',
            name: 'Practice',
            component: Practice
        }
    ]
});


export default router;