<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>  -->
   
      <Header/>
      <CrudForm/>
      <Footer/>
  
  <router-view/>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import CrudForm from '@/components/CrudForm.vue'
export default {
  components:{
    Header,
    Footer,
    CrudForm,
  }
}
</script>

<style>




/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
} */
</style>
