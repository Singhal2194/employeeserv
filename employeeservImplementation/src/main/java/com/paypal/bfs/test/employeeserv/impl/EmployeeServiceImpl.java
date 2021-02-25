package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDao;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.utils.EmployeeUtils;
import com.paypal.bfs.test.employeeserv.utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;


/**
 * Implementation class for employee service.
 */

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RequestValidator requestValidator;


    @Override
    public Employee addEmployee(Employee employee) {

        // if id is not present in the request, it will be generated automatically
        if (employee.getId() == null) {
            employee.setId(EmployeeUtils.generateUniqueEmpId());
        }
        // validate request
        requestValidator.validateCreateRequest(employee);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpId(employee.getId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDob(Date.valueOf(employee.getDateOfBirth()));

        employeeDao.save(employeeEntity);
        addEmployeeAddress(employee.getId(), employee.getAddress());
        return employee;
    }

    private void addEmployeeAddress(Integer id, Address address) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setEmpId(id);
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setState(address.getState());
        addressEntity.setZipCode(address.getZipCode());

        addressDao.save(addressEntity);
    }

    @Override
    public Employee getEmployeeById(String id) {

        Integer empId = Integer.valueOf(id);
        Optional<EmployeeEntity> employeeEntity = employeeDao.findByEmpId(empId);
        if (!employeeEntity.isPresent()) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(empId);
        employee.setFirstName(employeeEntity.get().getFirstName());
        employee.setLastName(employeeEntity.get().getLastName());
        employee.setDateOfBirth(employeeEntity.get().getDob().toString());
        employee.setAddress(getEmployeeAddress(empId));
        return employee;
    }

    private Address getEmployeeAddress(Integer empId) {

        Optional<AddressEntity> addressEntity = addressDao.findByEmpId(empId);
        Address address = new Address();
        address.setLine1(addressEntity.get().getLine1());
        address.setLine2(addressEntity.get().getLine2());
        address.setCity(addressEntity.get().getCity());
        address.setState(addressEntity.get().getState());
        address.setCountry(addressEntity.get().getCountry());
        address.setZipCode(addressEntity.get().getZipCode());
        return address;
    }

}
