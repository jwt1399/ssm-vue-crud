<template>
  <el-header class="header">
    <!-- <div class="nav"> -->
      <a href="/" class="nav-link">
        <img src="../assets/favicon.png" alt="logo" class="nav-logo" />
        <h2 class="nav-title">SSM-VUE-CRUD</h2>
      </a>
    <!-- </div> -->
    <el-button type="default" size="default" :plain="true" @click="skipAbout()">关于</el-button>
    <Dialog ref="dialog"/>
  </el-header>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { Search } from "@element-plus/icons-vue";
import { ElMessage, ElMessageBox } from "element-plus";
import Dialog from '@/components/Dialog.vue';
// import { defineProps } from "vue";

import { inject } from "vue";

const dialog =ref(null)

const defaultText = ref("测试");

const searchChange = (val) => {
  console.log(val);
  // getEmployeeList();
};

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

// 添加按钮
const handleAdd = (index, row) => {
  console.log(index, row);
    dialog.value.editNameDisabled = false;
    dialog.value.dialogFormVisible = true;
};

// 批量删除按钮
const batchDeleteEmployee = () => {
  if (batchIds.length == 0) {
    ElMessage({
      message: "最少选择一位员工！",
      type: "warning",
    });
    return;
  }
  ElMessageBox.confirm("此操作将永久删除该信息, 是否继续?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      axios
        .batchDeleteEmployee({
          ids: batchIds,
        })
        .then((res) => {
          ElMessage({
            type: "success",
            message: res.msg,
          });
          this.getEmployeeList();
        })
        .catch((res) => {
          ElMessage({
            type: "info",
            message: res.msg,
          });
        });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "删除取消",
      });
    });
};
</script>

<style scoped>
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

/* .nav-item {
  display: flex;
  align-items: center;
}

.nav-search {
  width: 150px;
  margin: 0 20px;
} */

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  border-bottom: 1px solid #eee;
  min-width: 800px;
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
