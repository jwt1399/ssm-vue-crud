package com.jianjian.crud.controller;

import com.jianjian.crud.bean.Department;
import com.jianjian.crud.bean.Msg;
import com.jianjian.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author：简简
 * @createTime：[2022/6/16 14:29]
 **/
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     *查询所有部门
     */
    @RequestMapping(value ="/dept",method = {RequestMethod.GET})
    @ResponseBody
    public Msg getDepartments() {
        List<Department> dept = departmentService.getAllDept();
        return Msg.success().add("dept", dept);
    }
}
