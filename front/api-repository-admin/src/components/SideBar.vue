<template>
    <div class="side-bar left">
        <TheRightSideBarNavi></TheRightSideBarNavi>
    </div>
</template>
<script>
    import TheRightSideBarNavi from './sideBar/TheRightSideBarNavi.vue';
    export default {
        components: { TheRightSideBarNavi }
    };
</script>
<style scoped>
    .side-bar{color:black}
    .left{
    position:fixed; 
    top:0px; 
    left:0px; 
    width:250px; 
    height:1000px; 
    background:rgba(255,98,124,0.6); 
    padding:40px 0; 
    overflow: hidden
    }
</style>