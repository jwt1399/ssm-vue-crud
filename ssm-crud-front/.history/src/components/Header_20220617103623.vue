<template>
  <el-header class="header">
      <a href="/" class="nav-link">
        <img class="nav-logo" src="../assets/favicon.png" alt="logo"/>
        <h2 class="nav-title">SSM-VUE-CRUD</h2>
      </a>
    <el-button type="default" size="default" :plain="true" @click="skipAbout()">关于</el-button>
  </el-header>
</template>

<script setup>
import { useRouter } from "vue-router";
import { ElMessage} from "element-plus";




//关于页跳转
const router = useRouter();
const skipAbout = () => {
  // window.location.href='#/about';
  // router.push({path:'about'})
  router.push({ name: "about" }); //路由名
  //弹框提示
  ElMessage({
    showClose: true,
    message: "这是本项目的关于页面！",
    center: true,
    type: "success",
  });
};


</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  border-bottom: 1px solid #eee;
  min-width: 800px;
}
.nav-logo {
  height: 45px;
  width: 45px;
  vertical-align: middle;
  display: inline-block;
}
.nav-title {
  /* font-size: 2rem; */
  display: inline-block;
  vertical-align: middle;
  color: #79bbff;
  padding-top: 10px;
}
</style>

<!-- 不使用script setup语法糖 -->
<!-- <script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Search } from '@element-plus/icons-vue'
export default {
    name: 'Header',
    setup() {
        const defaultText = ref("测试")
        const router = useRouter()
        const skipAbout = ()=>{
        // window.location.href='#/about';
        // router.push({path:'about'})
        router.push({ name: 'about'})
        }
        return { 
            skipAbout,
            Search,
            defaultText,
        }
    },
}
</script> -->
