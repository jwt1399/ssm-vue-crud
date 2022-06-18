<template>
        <el-dialog v-model="editDialogFormVisible" :title="editNameDisabled?'编辑员工':'添加员工'" @close="dialogClose">
        <el-form :model="editEmpForm" :rules="editEmpFormRules" ref="formRef">
            <el-form-item label="姓名" prop="empName">
                <el-input v-model="editEmpForm.empName" :disabled="editNameDisabled"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="editEmpForm.email"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <div>
                    <el-radio v-model="editEmpForm.gender" label="男" size="large">男</el-radio>
                    <el-radio v-model="editEmpForm.gender" label="女" size="large">女</el-radio>
                </div>
            </el-form-item>
            <el-form-item label="部门">
                <el-select v-model="editEmpForm.did" placeholder="选择部门">
                    <el-option v-for="(item,index) in dept" key="index" :label="item.deptName"
                               :value="item.deptId"></el-option>
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