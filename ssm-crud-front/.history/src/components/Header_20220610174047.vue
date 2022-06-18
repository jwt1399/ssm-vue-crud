<template>
 <el-header class="header">
    <div class="nav">
        <a href="/" class="nav-link">
            <img src="../assets/favicon.png" alt="logo" class="nav-logo">
            <h1 class="nav-title ">SSM-VUE-CRUD</h1>
        </a>
    </div>
    <div class="nav-item">
        <el-dropdown>
            <el-button size="large">
                全部
                <el-icon class="el-icon--right">
                    <arrow-down />
                </el-icon>
            </el-button>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item>Action 1</el-dropdown-item>
                    <el-dropdown-item>Action 2</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>

        <div class="nav-search">
            <el-input 
            placeholder="请输入名字" 
            v-model="defaultText" 
            size="large" 
            :prefix-icon="Search"
            @change="searchChange"
            clearable >
            </el-input>
            
        </div>
        <el-button type="primary" size="large" @click="handleadd()">新增</el-button>
        <el-button type="danger" size="large" @click="batchDeleteEmployee()">删除</el-button>
        <el-button type="default" size="large" :plain="true"  @click="skipAbout()">关于</el-button>
    </div>
    <Dialog ref="dialog"/>
</el-header>
</template>


<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Search } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

import Dialog from '@/components/Dialog.vue';
const dialog =ref(null)

const defaultText = ref("测试")

//关于页跳转
const router = useRouter()
const skipAbout = ()=>{
    // window.location.href='#/about';
    // router.push({path:'about'})
    router.push({ name: 'about'})//路由名
    //弹框提示
    ElMessage({
    showClose: true,
    message: '这是本项目的关于页面！',
    center: true,
    type: 'success',
  })
}

// 添加按钮
const handleAdd = (index,row)=>{
    console.log()
    dialog.value.editNameDisabled = true
    dialog.value.dialogFormVisible = true;
} 
</script>


<style scoped>
.nav-logo {
	height: 45px;
	width: 45px;
	vertical-align: middle;
	display: inline-block;
}

.nav-title {
	font-size: 2rem;
	display: inline-block;
	vertical-align: middle;
	color: #79bbff;
	padding-top: 10px;
}

.nav-item {
	display: flex;
	align-items: center;
}

.nav-search {
	width: 250px;
	margin: 0 20px;
}

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