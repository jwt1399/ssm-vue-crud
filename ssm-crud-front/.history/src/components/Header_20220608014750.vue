<template>
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
            :prefix-icon="Search" 
            v-model="search" 
            size="large" 
            @change="searchChange"
            clearable />
        </div>
        <el-button type="primary" size="large" @click="addEmployee()">新增</el-button>
        <el-button type="danger" size="large" @click="batchDeleteEmployee()">删除</el-button>
    </div>
</template>

<script>
export default {
  name: 'Header',
}
</script>