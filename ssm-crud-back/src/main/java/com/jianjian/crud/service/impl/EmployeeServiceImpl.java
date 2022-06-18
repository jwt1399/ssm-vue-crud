package com.jianjian.crud.service.impl;

import com.jianjian.crud.bean.Employee;
import com.jianjian.crud.mapper.EmployeeMapper;
import com.jianjian.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：简简
 * @createTime：[2022/6/4 22:52]
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    @Override
    public void deleteEmp(Integer empId) {
         employeeMapper.deleteByPrimaryKey(empId);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public Employee getEmp(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    @Override
    public int deleteBatchEmp(List<Integer> empIds) {
        return employeeMapper.deleteBatchByIds(empIds);
    }

    @Override
    public void addEmp(Employee employee) {
        employeeMapper.insert(employee);
    }
}
