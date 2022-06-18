import com.github.pagehelper.PageInfo;
import com.jianjian.crud.bean.Employee;
import com.jianjian.crud.bean.Msg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author：简简
 * @createTime：[2022/6/4 23:05]
 **/
@WebAppConfiguration
@SpringJUnitConfig(locations = {"classpath:applicationContext.xml", "classpath:springMVC.xml"})
public class MVCTest {

    //传入MVC的IOC容器
    @Autowired
    WebApplicationContext context;

    //虚拟MVC请求，获取处理结果
    MockMvc mockMvc;

    @BeforeEach
    public void initMokcMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("pageNum", "1").param("pageSize","5")).andReturn();
        //请求成功以后，请求域中会有pageInfo，可以取出来验证
        MockHttpServletRequest request = result.getRequest();
        Msg pi = (Msg) request.getAttribute("pageInfo");
        System.out.println("当前页码："+pi);
//        System.out.println("总页码："+pi.getPages());
//        System.out.println("总记录数："+pi.getTotal());
//        System.out.println("在页面需要连续显示的页码");
//        for (int i : pi.getNavigatepageNums()) {
//            System.out.println(" " + i);
//        }
//        //获取员工数据
//        List<Employee> list = pi.getList();
//        for (Employee employee : list) {
//            System.out.println("ID："+employee.getEmpId()+"==>Name:"+employee.getEmpName());
//        }
    }

}