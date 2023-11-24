<template>
    <h1>구동중인 Resourcers</h1>
    <div>
        <div v-if="cachedResourcerList.length === 0">no data</div>
        <div v-else>
            <table>
                <tr>
                    <th>no</th>
                    <th>name</th>
                    <th>path</th>
                    <th>description</th>
                    <th>enabled</th>
                </tr>
                <tr v-for="(resourcer, index) in cachedResourcerList" key="resourcer.id">
                    <td>{{ index }}</td>
                    <td>{{ resourcer.name }}</td>
                    <td>{{ resourcer.path }}</td>
                    <td>{{ resourcer.description }}</td>
                    <td>{{ resourcer.enabled }}</td>
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
            cachedResourcerList:[],
        };
    },
    mounted(){
        this.initCachedResourcerList();
    },
    computed:{
    },
    watched:{
    },
    methods: {
        initCachedResourcerList: async () => {
            this.routerList = await axios.get(requestRouteInfo.CachedResourcerList.path);
        }
    },
}
</script>
<style scoped>

</style>