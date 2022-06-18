<template>
  <el-main>
    <div class="nav-item">
      <el-dropdown>
        <el-button size="default">
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
          size="default"
          :prefix-icon="Search"
          @change="searchChange"
          clearable
        >
        </el-input>
      </div>
      <el-button type="primary" size="default" @click="handleAdd()">新增</el-button>
      <el-button type="danger" size="default" @click="batchDeleteEmployee()">删除</el-button>
    </div><br/>

    <div class="data-table">
      <el-table
        :data="employeeList"
        border
        style="width: 100%"
        v-loading="loading"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="empId" label="id" sortable></el-table-column>
        <el-table-column prop="empName" label="姓名"></el-table-column>
        <el-table-column prop="gender" label="性别" :formatter="formatterSex"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="department.deptName" label="部门"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="primary"
              size="large"
              :icon="Edit"
              @click="handleEdit(scope.$index, scope.row)"
              circle
            >
            </el-button>
            <el-button
              type="danger"
              size="large"
              :icon="Delete"
              @click="handleDelete(scope.$index, scope.row)"
              circle
            >
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="table-pagination">
      <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 15, 20]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </el-main>
  <!-- <Dialog ref="dialog"/> -->
  <el-dialog
    v-model="dialogFormVisible"
    :title="editNameDisabled ? '修改' : '添加'"
    @close="dialogClose"
  >
    <el-form
      :model="EmpForm"
      :rules="rules"
      ref="formRef"
      label-position="right"
      label-width="80px"
      status-icon
    >
      <el-form-item label="姓名" prop="empName">
        <el-input v-model="EmpForm.empName" :disabled="editNameDisabled"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="EmpForm.email"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="EmpForm.gender">
          <el-radio :label="1" border size="default">男</el-radio>
          <el-radio :label="2" border size="default">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="所属部门" prop="dId">
        <el-select v-model="EmpForm.dId" placeholder="请选择部门">

          <el-option v-for="(item,index) in DeptList" :key="index" :label="item.deptName" :value="item.deptId"> 
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
import { ElMessage, ElMessageBox } from "element-plus";
import { ref, onBeforeMount, reactive } from "vue";
import { Edit, Delete } from "@element-plus/icons-vue";
import axios from "axios";
// import Dialog from '@/components/Dialog.vue';

// const dialog =ref(null)
//分页相关
const pageNum = ref(1);
const pageSize = ref(5);
const small = ref(false);
const background = ref(true);
const disabled = ref(false);
const total = ref(null);
//每页条目数
const handleSizeChange = (val) => {
  console.log(`每页条目数：${val}`);
  getEmployeeList(pageNum.value, val);
};
//当前页面
const handleCurrentChange = (val) => {
  console.log(`当前页面: ${val}`);
  getEmployeeList(val, pageSize.value);
};

//勾选框改变
let batchIds = ref(null);
const handleSelectionChange = (arr) => {
  console.log(arr);
  var arrs = [];
  for (var i = 0; i < arr.length; i++) {
    arrs.push(arr[i].empId);
  }
  batchIds = arrs.join();
  console.log(batchIds);
};

//获取员工数据
const employeeList = ref([]);
const loading = ref(true); //加载动画
function getEmployeeList(pageNum, pageSize) {
  axios
    .get("api/ssm_crud_back/employees/", {
      params: {
        pageNum: pageNum,
        pageSize: pageSize,
      },
    })
    .then(function (res) {
      console.log("所有员工信息：" , res.data);
      loading.value = false;
      employeeList.value = res.data.data.pageInfo.list;
      total.value = res.data.data.pageInfo.total;
      pageSize = res.data.data.pageInfo.pageSize;
      pageNum = res.data.data.pageInfo.pageNum;
    })
    .catch(function (error) {
      console.log(error);
    });
}
// 性别格式化
const formatterSex = (row, column) => {
  return row.gender == 1 ? "男" : "女";
};

// 删除按钮
const handleDelete = (index, row) => {
  console.log(index, row);
  ElMessageBox.confirm("此操作将永久删除该信息, 是否继续?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteEmployee(row.empId);
    })
    .catch(() => {
      ElMessage({ type: "info", message: "删除取消" });
    });
};
// 删除员工信息
function deleteEmployee(empId) {
  axios
    .delete(`api/ssm_crud_back/delete/${empId}`)
    .then((response) => {
      if (response.data.code == 100) {
        getEmployeeList();
        ElMessage({ type: "success", message: "删除成功" });
      } else {
        ElMessage({ type: "error", message: "删除失败" });
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

let EmpForm = ref({
  empName: "",
  email: "",
  gender: "",
  dId: "",
  department: {
    deptId: "",
    deptName: "",
  },
});

// const EmpForm = ref({});
// 查询单个员工信息
function getEmployee(empId) {
  axios
    .get(`api/ssm_crud_back/emp/${empId}`)
    .then((response) => {
        console.log("单个员工信息：",response.data);
        EmpForm.value = response.data.data.emp;
        console.log(EmpForm);
    })
    .catch((error) => {
        console.log(error);
    });
}

//显示/隐藏对话框
let dialogFormVisible = ref(false);
//名字是否可编辑
let editNameDisabled = ref(false);

// 修改按钮
const handleEdit = (index, row) => {
  console.log(index, row);
  console.log(row.empId);
  
  getEmployee(row.empId);
  getAllDept();
  editNameDisabled.value = true;
  dialogFormVisible.value = true;
};

// 更新员工信息
function updateEmp(params) {
  axios.put("api/ssm_crud_back/update", {...params})
    .then((response) => {
        console.log(response.data)
      if (response.data.code == 100) {
        ElMessage({ type: "success", message: "更新成功" });
        getEmployeeList();
      } else {
        ElMessage({ type: "error", message: "更新失败" });
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

onBeforeMount(() => {
  getEmployeeList();
});

let DeptList = ref({})

function getAllDept() {
    axios.get("api/ssm_crud_back/dept")
    .then(res => {
        console.log("部门信息：",res.data);
        DeptList.value = res.data.data.dept;
    })
    .catch(error => {
      console.log(error);
    });
}




//表单验证
const rules = reactive({
  empName: [
    {
      required: true,
      message: "Please input Activity name",
      trigger: "blur",
    },
    {
      min: 1,
      max: 15,
      message: "Length should be 1 to 15",
      trigger: "blur",
    },
  ],
  email: [
    {
      required: true,
      message: "请输入正确邮箱",
      trigger: "blur",
      pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
    },
  ],
  gender: [
    {
      required: true,
      message: "请选择性别",
      trigger: "blur",
    },
  ],
  dId: [
    {
      required: true,
      message: "请选择部门",
      trigger: "blur",
    },
  ],
  department: [
    {
      required: true,
      message: "请选择部门",
      trigger: "blur",
    },
  ],
});

let formRef = ref(null);
// onMounted(()=>{
//     console.log(formRef.value)
// });

//表单验证
function submitForm() {
  let flag = false;
  formRef.value.slotsvalidate((valid) => {
    if (valid) {
      flag = true;
    } else {
      flag = false;
      return false;
    }
  });
  return flag;
}

function resetForm() {
  formRef.value.resetFields();
  dialogFormVisible.value = false;
}

function dialogClose() {
  console.log(formRef.value);
  formRef.value.resetFields();
}

//提交
function addSubmit() {
//   const valid = submitForm();
  const valid = true;
  if (valid) {
    if (editNameDisabled) {
      updateEmp(EmpForm.value);
    } else {
      addEmp(EmpForm.value);
    }
    dialogFormVisible.value = false;
  }
}
</script>

<style scoped>
.table-pagination {
  display: flex;
  justify-content: center;
  padding: 20px;
}
.nav-item {
  display: flex;
  justify-content:center;
}

.nav-search {
  width: 150px;
  margin: 0 20px;
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
