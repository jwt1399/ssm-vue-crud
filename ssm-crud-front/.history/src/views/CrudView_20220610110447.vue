  <template>
    <el-main>
        <div class="data-table">
            <el-table 
            :data="employeeList" 
            border 
            style="width: 100%" 
            v-loading="loading"
            @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection"></el-table-column>
                <el-table-column prop="empId" label="Id" sortable></el-table-column>
                <el-table-column prop="empName" label="Name"></el-table-column>
                <el-table-column prop="gender" label="Gender" :formatter="formatterSex"></el-table-column>
                <el-table-column prop="email" label="Email"></el-table-column>
                <el-table-column prop="department.deptName" label="DeptName"></el-table-column>

                <el-table-column label="Operate">
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
</template>
 




<script setup>

</script>

<!-- 不使用script setup语法糖 -->
<!-- <script>
import Form from '@/components/Form.vue'
export default {
    name: 'CrudView',
    components: {
        Form,
    }
}
</script> -->