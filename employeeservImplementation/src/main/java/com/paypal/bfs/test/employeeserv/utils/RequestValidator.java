package com.paypal.bfs.test.employeeserv.utils;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class RequestValidator {

    @Autowired
    private EmployeeDao employeeDao;

    private void validateAddress(Address address) {

        if (Objects.isNull(address))
            throw new InvalidRequestException("address cannot be null/empty");
        if (StringUtils.isEmpty(address.getLine1()))
            throw new InvalidRequestException("line 1 cannot be null/empty");
        if (StringUtils.isEmpty(address.getCity()))
            throw new InvalidRequestException("city cannot be null/empty");
        if (StringUtils.isEmpty(address.getState()))
            throw new InvalidRequestException("state cannot be null/empty");
        if (StringUtils.isEmpty(address.getCountry()))
            throw new InvalidRequestException("country cannot be null/empty");
        if (address.getZipCode() == null)
            throw new InvalidRequestException("zip code cannot be null");
    }

    public void validateCreateRequest(Employee employee) {

        if (Objects.isNull(employee)) {
            throw new InvalidRequestException("request cannot be null");
        }

        if (StringUtils.isEmpty(employee.getFirstName())) {
            throw new InvalidRequestException("first name cannot be null/empty");
        }

        if (StringUtils.isEmpty(employee.getLastName())) {
            throw new InvalidRequestException("last name cannot be null/empty");
        }

        if (StringUtils.isEmpty(employee.getDateOfBirth())) {
            throw new InvalidRequestException("date of birth cannot be null/empty");
        }
        validateAddress(employee.getAddress());

        if (recordAlreadyExists(employee)) {
            throw new InvalidRequestException("Employee with given id already exists");
        }
    }

    private boolean recordAlreadyExists(Employee employee) {
        Optional<EmployeeEntity> employeeEntity = employeeDao.findByEmpId(employee.getId());
        return employeeEntity.isPresent();
    }
}
