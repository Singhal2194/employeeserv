package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(String id);
}
