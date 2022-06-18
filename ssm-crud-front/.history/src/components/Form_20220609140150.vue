  <template>
    <el-main>
        <div class="data-table">
            <el-table :data="employeeList" border style="width: 100%" v-loading="loading"
                @selection-change="handleSelectionChange">
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="empId" label="id"></el-table-column>
                <el-table-column prop="empName" label="姓名"></el-table-column>
                <el-table-column prop="gender" label="性别" :formatter="formatterSex"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column prop="department.deptName" label="部门名称"></el-table-column>

                <el-table-column label="操作">
                    <el-button type="primary" size="large" :icon="Edit" @click="handleEdit(scope.$index, scope.row)"
                        circle>
                    </el-button>

                    <el-button type="danger" size="large" :icon="Delete" @click="handleDelete(scope.$index, scope.row)"
                        circle>
                    </el-button>
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
                :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" 
            />
        </div>
    </el-main>

</template>
 
<script>
import { ref,onBeforeMount } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
export default {
    name:'Form',
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
        const employeeList =ref([])
        function getEmployeeList(pageNum,pageSize){
            loading.value=true
            axios.get("api/ssm_crud_back/employees/", 
            {
                params: {
                    pageNum: pageNum,
                    pageSize: pageSize,
                }
            })
            .then(function(res) {
                console.log(res.data)
                loading.value = false
                employeeList.value = res.data.data.pageInfo.list
                total.value = res.data.data.pageInfo.total
                pageSize = res.data.data.pageInfo.pageSize
                pageNum = res.data.data.pageInfo.pageNum
                })
                .catch(function(error) {
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
            loading
        }
    }
}
</script>



<style scoped>
.table-pagination {
	display: flex;
	justify-content: center;
	padding: 20px;
}
</style>
    