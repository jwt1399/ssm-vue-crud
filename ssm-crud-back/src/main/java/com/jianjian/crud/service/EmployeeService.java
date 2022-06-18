package com.jianjian.crud.service;

import com.jianjian.crud.bean.Employee;

import java.util.List;

/**
 * @author：简简
 * @createTime：[2022/6/4 22:54]
 **/
public interface EmployeeService {
    List<Employee> getAll();

    void deleteEmp(Integer empId);

    void updateEmp(Employee employee);

    Employee getEmp(Integer empId);

    int deleteBatchEmp(List<Integer>  empIds);

    void addEmp(Employee employee);
}
