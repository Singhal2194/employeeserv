package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public class TestUtils {

    public static final String CREATE_REQUEST = "{\n" +
            "  \"id\": 125,\n" +
            "  \"first_name\": \"dsfsf\",\n" +
            "  \"last_name\": \"dffdv\",\n" +
            "  \"date_of_birth\": \"2020-02-23\",\n" +
            "  \"address\": {\n" +
            "    \"line1\": \"dfgh\",\n" +
            "    \"line2\": \"asdfr\",\n" +
            "    \"city\": \"bangalore\",\n" +
            "    \"state\": \"karnataka\",\n" +
            "    \"country\": \"india\",\n" +
            "    \"zip_code\": 123\n" +
            "  }\n" +
            "}";

    public static String getCreateRequest() {
        return CREATE_REQUEST;
    }

    public static Employee getEmployeeCreateRequest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employeeObj = objectMapper.readValue(getCreateRequest(), Employee.class);
        return employeeObj;
    }

    public static Employee getEmployeeCreateResponse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employeeObj = objectMapper.readValue(getCreateRequest(), Employee.class);
        return employeeObj;
    }

    public static Optional<EmployeeEntity> getEmployeeEntity() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpId(123);
        employeeEntity.setFirstName("satish");
        employeeEntity.setLastName("agarwal");
        employeeEntity.setDob(Date.valueOf("2020-02-25"));
        return Optional.of(employeeEntity);
    }

    public static Optional<AddressEntity> getAddressEntity() {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setEmpId(123);
        addressEntity.setLine1("road no 2, DC colony");
        addressEntity.setLine2("Main Road");
        addressEntity.setCity("bangalore");
        addressEntity.setState("karnataka");
        addressEntity.setCountry("india");
        addressEntity.setZipCode(123);
        return Optional.of(addressEntity);
    }
}
