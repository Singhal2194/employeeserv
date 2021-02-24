package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> addEmployee(@Valid Employee employee) {

        Employee employeeData = employeeService.addEmployee(employee);
        return new ResponseEntity<>(employeeData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            throw new RecordNotFoundException();
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
