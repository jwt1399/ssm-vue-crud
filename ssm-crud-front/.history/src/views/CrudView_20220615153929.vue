<template>
    <el-main>
        <div class="data-table">
            <el-table 
                :data="employeeList" 
                border 
                style="width: 100%" 
                v-loading="loading"
                @selection-change="handleSelectionChange">
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="empId" label="Id" sortable></el-table-column>
                <el-table-column prop="empName" label="Name"></el-table-column>
                <el-table-column prop="gender" label="Gender" :formatter="formatterSex"></el-table-column>
                <el-table-column prop="email" label="Email"></el-table-column>
                <el-table-column prop="department.deptName" label="DeptName"></el-table-column>
                <el-table-column label="Operations">
                    <template #default="scope">
                        <el-button type="primary" 
                            size="large" 
                            :icon="Edit" 
                            @click="handleEdit(scope.$index, scope.row)"
                            circle
                        ></el-button>
                        <el-button 
                            type="danger" 
                            size="large" 
                            :icon="Delete" 
                            @click="handleDelete(scope.$index, scope.row)"
                            circle
                        ></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="table-pagination">
            <el-pagination 
                v-model:current-page="pageNum" 
                v-model:page-size="pageSize" 
                :page-sizes="[5, 10, 15, 20]"
                :small="small" :disabled="disabled" 
                :background="background"
                layout="total, sizes, prev, pager, next, jumper" 
                :total="total" 
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange" 
            />
        </div>
    </el-main>
    <Dialog ref="dialog"/>
</template>
 


<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import { ref,onBeforeMount } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
import Dialog from '@/components/Dialog.vue';


const dialog =ref(null)
//分页相关
const pageNum = ref(1)
const pageSize = ref(5)
const small = ref(false)
const background = ref(true)
const disabled = ref(false)
const total = ref(null)
//每页条目数
const handleSizeChange = (val) => {
    console.log(`每页条目数：${val}`)
    getEmployeeList(pageNum.value,val)
}
//当前页面
const handleCurrentChange = (val) => {
    console.log(`当前页面: ${val}`)
    getEmployeeList(val,pageSize.value)
}

//勾选框改变
let batchIds = ref(null)
const handleSelectionChange = (arr) => {
    console.log(arr)
    var arrs = [];
    for (var i = 0; i < arr.length; i++) {
        arrs.push(arr[i].empId);
    }
    batchIds = arrs.join();
    console.log(batchIds)
}

//编辑按钮
const handleEdit = (index, row) => {
    console.log(index, row)
    dialog.value.editNameDisabled = true
    dialog.value.dialogFormVisible = true;
}
 // 删除按钮
const handleDelete = (index, row) => {
    console.log(index, row)
    ElMessageBox.confirm(
        '此操作将永久删除该信息, 是否继续?','提示',
    {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(() => {
        ElMessage({
        type: 'success',
        message: '删除成功',
        })
    }).catch(() => {
        ElMessage({
        type: 'info',
        message: '删除取消',
        })
    })
}

//获取员工数据
const employeeList =ref([])
const loading = ref(true)//加载动画
function getEmployeeList(pageNum,pageSize){
    axios.get("api/ssm_crud_back/employees/", {
        params: {
            pageNum: pageNum,
            pageSize: pageSize,
        }
    }).then(function(res) {
        console.log(res.data)
        loading.value = false
        employeeList.value = res.data.data.pageInfo.list
        total.value = res.data.data.pageInfo.total
        pageSize = res.data.data.pageInfo.pageSize
        pageNum = res.data.data.pageInfo.pageNum
    }).catch(function(error) {
        console.log(error);
    });
}
//删除员工数据
function deleteEmployee(id) {
        axios.get({
          id
        }).then(res => {
          this.$message({
            type: 'success',
            message: res.msg
          });
          this.getEmployeeList();
        })
      },

onBeforeMount(()=>{
    getEmployeeList();
})
// 性别格式化
const formatterSex = (row, column)=>{
    return row.gender == 1 ? "男" : "女";
};


</script>

<style scoped>
.table-pagination {
	display: flex;
	justify-content: center;
	padding: 20px;
}
</style>


<!-- 不使用script setup语法糖 -->
<!-- <script>
import { ref,onBeforeMount } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
export default {
    name: 'CrudView',
    setup(){
        //分页相关
        const pageNum = ref(1)
        const pageSize = ref(5)
        const small = ref(false)
        const background = ref(true)
        const disabled = ref(false)
        const total = ref(null)
        const handleSizeChange = (val) => {
            console.log(`每页条目数：${val}`)
            getEmployeeList(pageNum.value,val)
        }
        const handleCurrentChange = (val) => {
            console.log(`当前页面: ${val}`)
            getEmployeeList(val,pageSize.value)
        }

        //获取员工数据
        const loading = ref(false)
        let batchIds = ref(null)
        const handleSelectionChange = (arr) => {
            console.log(arr)
            var arrs = [];
            for (var i = 0; i < arr.length; i++) {
                arrs.push(arr[i].empId);
            }
            batchIds = arrs.join();
            console.log(batchIds)
        }

        const handleEdit = (index, row) => {
            console.log(index, row)
        }
        
        const handleDelete = (index, row) => {
            console.log(index, row)
        }
        const employeeList =ref([])
        function getEmployeeList(pageNum,pageSize){
            axios.get("api/ssm_crud_back/employees/", {
                params: {
                    pageNum: pageNum,
                    pageSize: pageSize,
                }
            }).then(function(res) {
                console.log(res.data)
                loading.value = false
                employeeList.value = res.data.data.pageInfo.list
                total.value = res.data.data.pageInfo.total
                pageSize = res.data.data.pageInfo.pageSize
                pageNum = res.data.data.pageInfo.pageNum
            }).catch(function(error) {
                console.log(error);
            });
        }
        onBeforeMount(()=>{
            getEmployeeList();
        })
        // 性别格式化
        const formatterSex = (row, column)=>{
            return row.gender == 1 ? "男" : "女";
        };

        return{
            employeeList,
            getEmployeeList,
            pageNum,
            pageSize,
            total,
            small,
            disabled,
            background,
            formatterSex,
            handleSizeChange,
            handleCurrentChange,
            Edit,
            Delete,
            loading,
            handleSelectionChange,
            handleEdit,
            handleDelete,
        }
    }
}
</script> -->
