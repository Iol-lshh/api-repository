<template>
    <h1>Router {{routerInfo.id}}</h1>
    <form submit.prevent="sendPost">
        <div>
            <ul>
                <li>라우터</li>
                <li>이름: <input type="text" v-model="routerInfo.name"/></li>
                <li>경로: <input type="text" v-model="routerInfo.path"/></li>
                <li>설명: <input type="text" v-model="routerInfo.description"/></li>
                <li>사용여부: <input type="checkbox" v-model="routerInfo.enabled"/></li>
            </ul>
            <ul>
                <li>
                    리소서: <select v-model="routerInfo.enabled">
                        <option v-for="resourcerInfo in resourcerList" :value="resourcerInfo.id">{{ resourcerInfo.name }}</option>
                    </select>
                </li>
                <!-- 드롭다운에 의해 반응 -->
                <li>리소서</li>
                <li>이름: {{ resourcerInfo.name }}</li>
                <li>설명: {{ resourcerInfo.description }}</li>
                <li>사용여부: {{ resourcerInfo.enabled }}</li>
            </ul>
            <ul>
                <li>
                    쿼리: <select v-model="routerInfo.enabled">
                        <option v-for="queryInfo in queryList" :value="queryInfo.id">{{ queryInfo.name }}</option>
                    </select>
                </li>
                <li>쿼리</li>
                <li>이름: {{ queryInfo.name }}</li>
                <li>내용: {{ queryInfo.contents }}</li>
                <li>설명: {{ queryInfo.description }}</li>
                <li>사용여부: {{ queryInfo.enabled }}</li>
            </ul>
        </div>
    </form>
    <router-link :to="'/router/view/'+ routerInfo.id">저장</router-link>
    <button @click="submit()">저장</button>&nbsp;&nbsp;
    <router-link :to="'/router/view/'+ routerInfo.id">취소</router-link>
</template>
<script>
import axios from 'axios';
import requestRouteInfo from '../../assets/requestRouteInfo';

export default{
    data(){
        return {
            routerInfo: {},
            resourcerInfo: {},
            queryInfo: {},
            queryParameters: [],

            orignData: {
                routerInfo: {},
                resourcerInfo: {},
                queryInfo: {},
                queryParameters: [],
            },
        };
    },
    mounted(){
        //this.initView();
        this.setViewByData(this.getTestData());
    },
    computed:{
    },
    watched:{
    },
    methods: {
        initView: async () => {
            //const data = await axios.get(requestRouteInfo.RouterView.path);
            this.setViewByData(data);
        },
        setViewByData(data) {
            console.log(data);
            this.routerInfo = data.router;
            this.resourcerInfo = data.resourcer;
            this.queryInfo = data.query_view.query;
            this.queryParameters = data.query_view.queryParameters;

            this.orignData.routerInfo = data.router;
            this.orignData.resourcerInfo = data.resourcer;
            this.orignData.queryInfo = data.query_view.query;
            this.orignData.queryParameters = data.query_view.queryParameters;
        },
        getTestData() {
            return {
                "router": {
                    "id": 0,
                    "name": "string",
                    "path": "string",
                    "description": "string",
                    "query_id": 0,
                    "enabled": true
                },
                "resourcer": {
                    "id": 0,
                    "name": "string",
                    "path": "string",
                    "description": "string",
                    "access_name": "string",
                    "key": "string",
                    "driver": "string",
                    "driver_class_name": "string",
                    "enabled": true
                },
                "query_view": {
                    "query": {
                    "id": 0,
                    "name": "string",
                    "contents": "string",
                    "description": "string",
                    "resourcer_id": 0,
                    "enabled": true
                    },
                    "query_parameters": [
                        {
                            "id": 0,
                            "name": "string",
                            "description": "string",
                            "io_type": "string",
                            "is_optional": true,
                            "query_id": 0,
                            "enabled": true,
                            "optional": true
                        }
                    ]
                }
            }
        },
        submit(){
            
        },
        
    },
}
</script>
<style scoped>

</style>