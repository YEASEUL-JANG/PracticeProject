import { createRouter, createWebHistory } from 'vue-router';
import Home from '../pages/index.vue';
import Todos from '../pages/todos/index.vue';
import TodoCreate from '../pages/todos/create/index.vue';
import Practice from '../pages/practice/test6.vue';
import Test from '../pages/practice/test3_routercomp.vue';
import Todo from '../pages/todos/_id.vue';
import Homepage from '../pages/practice/test3_homepage.vue';
import List from '../pages/practice/test3_list.vue';

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
            path:'/todos/create',
            name: 'TodoCreate',
            component: TodoCreate
        },
        {
            path:'/todos/:id',
            name: 'Todo',
            component: Todo
        },

        {
            path:'/practice',
            name: 'Practice',
            component: Practice
        },
        {
            path:'/test',
            name: 'Test',
            component: Test
        },
        {
            path:'/homepage',
            name: 'Homepage',
            component: Homepage
        },
        {
            path:'/list',
            name: 'List',
            component: List
        },
    ]
});


export default router;