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
                    <td>{{ routerInfo.name }}</td>
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
export default{
    data(){
        return {
            routerList:[],
        };
    },
    mounted(){
        this.initRouterList();
    },
    computed:{
    },
    watched:{
    },
    methods: {
        initRouterList: async () => {
            this.routerList = await axios.get('http://localhost:8080/api-repository/router/list/all');
        }
    },
}
</script>
<style scoped>

</style>