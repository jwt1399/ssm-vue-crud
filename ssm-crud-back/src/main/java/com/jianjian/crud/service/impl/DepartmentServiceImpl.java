package com.jianjian.crud.service.impl;

import com.jianjian.crud.bean.Department;
import com.jianjian.crud.mapper.DepartmentMapper;
import com.jianjian.crud.mapper.EmployeeMapper;
import com.jianjian.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：简简
 * @createTime：[2022/6/16 14:35]
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDept() {
        return departmentMapper.selectByExample(null);
    }
}
