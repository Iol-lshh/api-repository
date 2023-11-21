// import axios from 'axios';
import { createRouter, createWebHistory } from 'vue-router';
import TheResourcerInfoList from './page/resourcerInfo/TheResourcerInfoList.vue';
import TheResourcerInfoView from './page/resourcerInfo/TheResourcerInfoView.vue';
import TheResourcerInfoForm from './page/resourcerInfo/TheResourcerInfoForm.vue';
import TheQueryInfoList from './page/queryInfo/TheQueryInfoList.vue';
import TheQueryInfoView from './page/queryInfo/TheQueryInfoView.vue';
import TheQueryInfoForm from './page/queryInfo/TheQueryInfoForm.vue';
import TheRouterInfoList from './page/routerInfo/TheRouterInfoList.vue';
import TheRouterInfoView from './page/routerInfo/TheRouterInfoView.vue';
import TheRouterInfoForm from './page/routerInfo/TheRouterInfoForm.vue';
import TheCachedResourcerInfoList from './page/resourcerInfo/TheCachedResourcerInfoList.vue';
import NotFound from './page/NotFound.vue';

// import routerHelper from './common/routerHelper.js';
// const preMenuList = await axios.get('localhost:8080/menu/all',{});
// const naviList = routerHelper.toTree(preMenuList);

const naviList = [
    { path: '/', redirect: '/resourcer/cached' },
    {
        name: 'cached-resourcer-info',
        path: '/resourcer/cached',
        components: {default: TheCachedResourcerInfoList}
    },
    {
        name: 'resourcer-info',
        path: '/resourcer',
        components: {default: TheResourcerInfoList},
        children: [
            {
                name: 'resourcer-info-view',
                path: '/view:resourcerInfoId',
                components: TheResourcerInfoView,
                props: true
            },
            {
                name: 'resourcer-info-form',
                path: '/form:resourcerInfoId',
                components: TheResourcerInfoForm,
                props: true
            },
        ]
    },
    {
        name: 'query-info',
        path: '/query',
        components: {default: TheQueryInfoList},
        children: [
            {
                name: 'query-view',
                path: '/view:queryInfoId',
                components: TheQueryInfoView,
                props: true
            },
            {
                name: 'query-form',
                path: '/form:queryInfoId',
                components: TheQueryInfoForm,
                props: true
            },
        ]
    },
    {
        name: 'router-info',
        path: '/router',
        components: {default: TheRouterInfoList},
        children: [
            {
                name: 'router-view',
                path: '/view:routerInfoId',
                components: TheRouterInfoView,
                props: true
            },
            {
                name: 'router-form',
                path: '/form:routerInfoId',
                components: TheRouterInfoForm,
                props: true
            },
        ]
    },
    { path: '/:notFound(.*)', component: NotFound }
]

const router = createRouter({
    history: createWebHistory(), 
    routes: [
        ...naviList
    ]
});

export default router;

