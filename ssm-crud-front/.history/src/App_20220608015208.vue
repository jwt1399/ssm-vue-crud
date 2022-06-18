<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
    <el-header class="header">
      <Header/>
    </el-header>
    
  </nav>
  <router-view/>
</template>

<script>
import Header from '@/components/Header.vue'
export default {
  components:{
    Header
  }
}
</script>

<style>
.header {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	border-bottom: 1px solid #eee;
	min-width: 800px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
