<template>
    <h1>Router {{routerInfo.id}}</h1>
    <div>
        <ul>
            <li>라우터</li>
            <li>이름: {{ routerInfo.name }}</li>
            <li>경로: {{ routerInfo.path }}</li>
            <li>설명: {{ routerInfo.description }}</li>
            <li>사용여부: {{ routerInfo.enabled }}</li>           
        </ul>
        <ul>
            <li>리소서</li>
            <li>이름: {{ resourcerInfo.name }}</li>
            <li>설명: {{ resourcerInfo.description }}</li>
            <li>사용여부: {{ resourcerInfo.enabled }}</li>
        </ul>
        <ul>
            <li>쿼리</li>
            <li>이름: {{ queryInfo.name }}</li>
            <li>내용: {{ queryInfo.contents }}</li>
            <li>설명: {{ queryInfo.description }}</li>
            <li>사용여부: {{ queryInfo.enabled }}</li>
        </ul>
    </div>
    <router-link :to="'/router/form/'+ routerInfo.id">수정</router-link>&nbsp;&nbsp;
    <router-link to="/router">목록</router-link>
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
            const data = await axios.get(requestRouteInfo.RouterView.path);
            this.setViewByData(data);
        },
        setViewByData(data) {
            console.log(data);
            this.routerInfo = data.router;
            this.resourcerInfo = data.resourcer;
            this.queryInfo = data.query_view.query;
            this.queryParameters = data.query_view.queryParameters;
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
        }
    },
}
</script>
<style scoped>

</style>