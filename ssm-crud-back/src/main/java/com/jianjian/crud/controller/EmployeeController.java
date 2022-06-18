package com.jianjian.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jianjian.crud.bean.Employee;
import com.jianjian.crud.bean.Msg;
import com.jianjian.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：简简
 * @createTime：[2022/6/4 22:52]
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 查询指定员工
     */
    @RequestMapping(value = "/emp/{empId}", method = {RequestMethod.GET})
    @ResponseBody
    public Msg getEmployee(@PathVariable("empId") Integer empId) {
        Employee employee = employeeService.getEmp(empId);
        return Msg.success().add("emp", employee);
    }

    /**
     * 分页查询所有员工
     */
    @RequestMapping(value = "/employees", method = {RequestMethod.GET})
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        // public Msg getEmpsWithJson(@PathVariable("pn") Integer pn) {
        //参数pageNum是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> emps = employeeService.getAll();
        //用PageInfo对结果进行包装 ，navigatePages 导航页号
        PageInfo page = new PageInfo(emps, 4);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 删除指定员工
     */
    @RequestMapping(value = "/delete/{empId}", method = {RequestMethod.DELETE})
    @ResponseBody
    public Msg deleteEmployee(@PathVariable("empId") Integer empId) {
        employeeService.deleteEmp(empId);
        return Msg.success();
    }

    /**
     * 更新员工信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    @ResponseBody
    public Msg updateEmployees(@RequestBody Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 批量删除员工
     */
    @RequestMapping(value = "/batch_delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public Msg deleteBatchEmployees(@RequestBody Map<String, Object> empIds) {
        List<Integer> Ids = (List<Integer>) empIds.get("empIds");
        employeeService.deleteBatchEmp(Ids);
        return Msg.success();
    }

    /**
     * 添加员工
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public Msg addEmployees(@RequestBody Employee employee) {
        employeeService.addEmp(employee);
        return Msg.success();
    }


}


