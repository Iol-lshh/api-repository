<template>
    <nav>
        <ul>
            <li>
                <router-link to="/router">router</router-link>
            </li>
            <li>
                <router-link to="/resourcer">resourcer</router-link>
            </li>
            <li>
                <router-link to="/query">query</router-link>
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
        // this.initMenuList();
    },
    computed:{
    },
    watched:{
    },
    methods: {
        // initMenuList: async () => {
        //     this.menuList = await axios.get('localhost:8080/menu/all', {});
        // },
    },
}
</script>
<style scoped>
nav {
    display: fixed;
    margin: 0;
    padding: 0;
    width: 100%;
    background-color: #1857e0;
}
ul {
    list-style: none;
    margin: 0;
    padding: 0;
    width: 100%;
    justify-content: center;
    align-items: center;
}
li {
    margin: 0;
    width: 100%;
}
a {
    text-decoration: none;
    background: transparent;
    border: 1px solid transparent;
    cursor: pointer;
    color: white;
    padding: 0.5rem 1.5rem;
    display: inline-block;
    width: 100%;
}

a:hover,
a:active,
a.active {
    color: #f1a80a;
    border-color: #f1a80a;
    background-color: #1857e0;
    width: 100%;
}
</style>