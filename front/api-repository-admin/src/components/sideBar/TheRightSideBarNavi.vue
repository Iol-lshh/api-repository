<template>
    <nav>
        <ul>
            <li v-for="navi in naviList">
                <router-link to="navi.path">{{ navi.name }}</router-link>
            </li>
        </ul>
    </nav>
</template>
<script>
import axios from 'axios';

export default{
    data(){
        return {
            naviMenuList: [],
        };
    },
    mounted(){
        this.initMenuList();
    },
    computed:{
    },
    watched:{
    },
    methods: {
        initMenuList: async () => {
            this.menuList = await axios.get('localhost:8080/menu/all', {});
        },
    },
}
</script>
<style scoped>

</style>