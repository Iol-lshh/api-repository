<script>
import Header from './components/Header.vue';
import SideBar from './components/SideBar.vue';
import Body from './components/Body.vue';
import Footer from './components/Footer.vue';

export default {
  components: {
    Header, SideBar, Body, Footer
  },
  data(){
    return{}
  },
};
</script>

<template>
  <Header></Header>
  <SideBar></SideBar>
  <Body></Body>
  <Footer></Footer>
</template>

<style scoped>

</style>
