package test;

import com.paypal.bfs.test.employeeserv.api.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDao;
import com.paypal.bfs.test.employeeserv.exception.CustomExceptionHandler;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;
import com.paypal.bfs.test.employeeserv.exception.RecordNotFoundException;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class EmployeeResourceTest {

    @InjectMocks
    private EmployeeResourceImpl employeeResource;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private AddressDao addressDao;

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;


    @Test
    public void addEmployee() throws IOException {
        Mockito.when(employeeService.addEmployee(Mockito.any())).thenReturn(TestUtils.getEmployeeCreateResponse());
        ResponseEntity<Employee> responseEntity = employeeResource.addEmployee(TestUtils.getEmployeeCreateRequest());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected = InvalidRequestException.class)
    public void addEmployeeIfNameMissing() throws IOException {
        Mockito.when(employeeService.addEmployee(Mockito.any())).thenThrow(new InvalidRequestException("First Name cannot be null/empty"));
        ResponseEntity<Employee> responseEntity = employeeResource.addEmployee(TestUtils.getEmployeeCreateRequest());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void retrieveRecord() throws IOException {
        Mockito.when(employeeService.getEmployeeById(Mockito.any())).thenReturn(TestUtils.getEmployeeCreateResponse());
        ResponseEntity<Employee> responseEntity = employeeResource.employeeGetById("123");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected = RecordNotFoundException.class)
    public void retrieveNotFoundRecord() {
        Mockito.when(employeeService.getEmployeeById(Mockito.any())).thenReturn(null);
        employeeResource.employeeGetById("123");
    }

}
