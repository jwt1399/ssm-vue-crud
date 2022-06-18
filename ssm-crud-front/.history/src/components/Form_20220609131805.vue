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
            <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[5, 10, 15, 20]"
                :small="small" :disabled="disabled" :background="background"
                layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </el-main>

</template>
 
<script>
export default {
    name:'Form'
}
</script>



<style scoped>
.table-pagination {
	display: flex;
	justify-content: center;
	padding: 20px;
}
</style>
    