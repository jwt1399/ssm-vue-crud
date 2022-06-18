<template>
    <el-dialog v-model="dialogFormVisible" :title="editNameDisabled ? '修改' : '添加'" @close="dialogClose">
        <el-form :model="form" :rules="rules" ref="formRef" size="default" label-position="right" label-width="80px" status-icon>
            <el-form-item label="姓名" prop="empName">
                <el-input v-model="form.empName" :disabled="editNameDisabled"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="form.gender">
                    <el-radio label="1" size="large">男</el-radio>
                    <el-radio label="2" size="large">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="部门" prop="dId">
                <el-select v-model="form.dId" placeholder="请选择部门">
                 <el-option label="Zone one" value="shanghai" />
                    <el-option label="Zone two" value="beijing" />
                    <!-- <el-option v-for="(item, index) in departmentList" :key="index" :label="item.deptName" :value="item.deptId"> -->
                    <!-- </el-option> -->
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="resetForm">取消</el-button>
                <el-button type="primary" @click="addSubmit">确定</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, reactive,defineExpose } from 'vue'
const form = reactive({
        empName: '测试',
        email: '',
        gender: '',
        dId: ''
    })
const departmentList = ref({})

//表单验证
const rules = reactive({
    empName: [{
        required: true,
        message: 'Please input Activity name',
        trigger: 'blur',
    },{
        min: 1,
        max: 15,
        message: 'Length should be 1 to 15',
        trigger: 'blur',
    }],
    email: [{
        required: true,
        message: '请输入正确邮箱',
        trigger: 'blur',
        pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    }],
    gender: [{
        required: true,
        message: '请选择性别',
        trigger: 'blur',
    }],
    dId: [{
        required: true,
        message: '请选择部门',
        trigger: 'blur',
    }]
})

//显示/隐藏对话框
const dialogFormVisible = ref(false)
//可编辑
const editNameDisabled = ref(false)
defineExpose({
    dialogFormVisible,
    editNameDisabled
})
</script>

