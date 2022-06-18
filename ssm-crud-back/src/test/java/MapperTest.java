import com.jianjian.crud.bean.Department;
import com.jianjian.crud.bean.Employee;
import com.jianjian.crud.mapper.DepartmentMapper;
import com.jianjian.crud.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;


@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testDept() {
        //新增部门信息
        departmentMapper.insertSelective(new Department(null, "简简部"));
    }

    @Test
    public void testEmp() {
        //插入一条员工数据
        employeeMapper.insertSelective(new Employee(null, "张三", 1, "zhangsan@qq.com", 1));
        employeeMapper.insertSelective(new Employee(null, "李四", 1, "lisi@163.com", 1));

    }
    @Test
    public void testBatchEmp() {
        //批量插入员工数据
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 100; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, 2, uid + "@gmail.com", 2));
        }
    }
}