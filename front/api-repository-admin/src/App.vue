<script>
import Header from './components/Header.vue';
import SideBar from './components/SideBar.vue';
import Footer from './components/Footer.vue';

export default {
  components: {
    Header, SideBar, Footer
  },
  data(){
    return{}
  },
};

</script>

<template>
  <Header></Header>
  <SideBar></SideBar>
  <main>
    <router-view></router-view>
  </main>
  <Footer></Footer>
</template>

<style scoped>

</style>
