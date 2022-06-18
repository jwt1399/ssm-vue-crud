<template>
    <el-dialog v-model="editDialogFormVisible" :title="editNameDisabled ? '编辑员工' : '添加员工'" @close="dialogClose">

        <el-form :model="editEmpForm" :rules="rules" ref="formRef">
            <el-form-item label="姓名" prop="empName">
                <el-input v-model="editEmpForm.empName" :disabled="editNameDisabled"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="editEmpForm.email"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="editEmpForm.gender">
                    <el-radio label="1" size="large">男</el-radio>
                    <el-radio label="2" size="large">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="部门">
                <el-select v-model="editEmpForm.dId" placeholder="请选择部门">
                    <el-option v-for="(item, index) in dept" key="index" :label="item.deptName" :value="item.deptId">
                    </el-option>
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
import {ref} from 'vue'
const editEmpForm = ref({})

            //表单验证
            rules: {
                empName: [
                    {
                        required: true,
                        message: 'Please input Activity name',
                        trigger: 'blur',
                    },
                    {
                        min: 1,
                        max: 15,
                        message: 'Length should be 1 to 15',
                        trigger: 'blur',
                    },
                ],
                email: [
                    {
                        required: true,
                        message: '请输入正确邮箱',
                        trigger: 'blur',
                        pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                    },
                ],
                gender: [
                    {
                        required: true,
                        message: '请选择性别',
                        trigger: 'blur',
                    },
                ],
                deptName: [
                    {
                        required: true,
                        message: '请选择部门',
                        trigger: 'blur',
                    },
                ],
            },
            editDialogFormVisible: false,
            //可编辑
            editNameDisabled: false,
</script>
