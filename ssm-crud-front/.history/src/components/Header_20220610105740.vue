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
                <!-- <template #prefix>
                    <el-icon class="el-input__icon"><search /></el-icon>
                </template> -->
            </el-input>
            
        </div>
        <el-button type="primary" size="large" @click="addEmployee()">新增</el-button>
        <el-button type="danger" size="large" @click="batchDeleteEmployee()">删除</el-button>
        <!-- <router-link to='/about'> -->
        <el-button type="default" size="large" @click="skipAbout()">关于</el-button>
        <!-- </router-link> -->
    </div>
</el-header>
</template>

<script>
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
</script>

<script setup>
    import { ref } from 'vue'
    import { useRouter } from 'vue-router'
    import { Search } from '@element-plus/icons-vue'
    const defaultText = ref("测试")
    const router = useRouter()
    const skipAbout = ()=>{
        // window.location.href='#/about';
        // router.push({path:'about'})
        router.push({ name: 'about'})//路由名
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