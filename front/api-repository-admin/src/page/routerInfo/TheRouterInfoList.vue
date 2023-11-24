<template>
    <h1>Router</h1>
    <div>
        <div v-if="routerList.length === 0">no data</div>
        <div v-else>
            <table>
                <tr>
                    <th>no</th>
                    <th>name</th>
                    <th>path</th>
                    <th>description</th>
                    <th>enabled</th>
                </tr>
                <tr v-for="(routerInfo, index) in routerList" key="routerInfo.id">
                    <td>{{ index }}</td>
                    <td><router-link :to="'/router/view/'+ routerInfo.id">{{ routerInfo.name }}</router-link></td>
                    <td>{{ routerInfo.path }}</td>
                    <td>{{ routerInfo.description }}</td>
                    <td>{{ routerInfo.enabled }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import requestRouteInfo from '../../assets/requestRouteInfo';
export default{
    data(){
        return {
            routerList: [],
        };
    },
    mounted(){
        //this.initRouterList();
        this.routerList = this.getTestData();
    },
    computed:{
    },
    watched:{
    },
    methods: {
        initRouterList: async () => {
            this.routerList = await axios.get(requestRouteInfo.RouterList.path);
        },
        getTestData: () => {
            return [
                {
                    "id": 1,
                    "name": "string",
                    "path": "string",
                    "description": "string",
                    "query_id": 1,
                    "enabled": true
                },
            ]
        }
    },
}
</script>
<style scoped>

</style>