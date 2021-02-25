package test;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDao;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;
import com.paypal.bfs.test.employeeserv.impl.EmployeeServiceImpl;
import com.paypal.bfs.test.employeeserv.utils.RequestValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private AddressDao addressDao;

    @Mock
    private RequestValidator requestValidator;


    @Test
    public void addEmployeeTestSuccess() throws IOException {

        employeeService.addEmployee(TestUtils.getEmployeeCreateRequest());
        EmployeeEntity employeeEntity = new EmployeeEntity();
        AddressEntity addressEntity = new AddressEntity();
        verify(employeeDao, Mockito.atLeast(0)).save(employeeEntity);
        verify(addressDao, Mockito.atLeast(0)).save(addressEntity);

    }

    @Test(expected = InvalidRequestException.class)
    public void addEmployeeTestIfAlreadyExists() throws IOException {

        Employee employee = TestUtils.getEmployeeCreateRequest();

        ArgumentCaptor<Employee> idCapture = ArgumentCaptor.forClass(Employee.class);
        doThrow(new InvalidRequestException("Employee with given id exists")).when(requestValidator).validateCreateRequest(idCapture.capture());

        employeeService.addEmployee(employee);
    }

    @Test(expected = InvalidRequestException.class)
    public void addEmployeeTestIfFirstNameIsMissing() throws IOException {

        Employee employee = TestUtils.getEmployeeCreateRequest();

        ArgumentCaptor<Employee> idCapture = ArgumentCaptor.forClass(Employee.class);
        doThrow(new InvalidRequestException("first cannot be null/empty")).when(requestValidator).validateCreateRequest(idCapture.capture());

        employeeService.addEmployee(employee);
    }

    @Test(expected = InvalidRequestException.class)
    public void addEmployeeTestIfDOBIsMissing() throws IOException {

        Employee employee = TestUtils.getEmployeeCreateRequest();

        ArgumentCaptor<Employee> idCapture = ArgumentCaptor.forClass(Employee.class);
        doThrow(new InvalidRequestException("dob cannot be null/empty")).when(requestValidator).validateCreateRequest(idCapture.capture());

        employeeService.addEmployee(employee);
    }

    @Test
    public void retrieveRecord() {

        Mockito.when(employeeDao.findByEmpId(Mockito.any())).thenReturn(TestUtils.getEmployeeEntity());
        Mockito.when(addressDao.findByEmpId(Mockito.any())).thenReturn(TestUtils.getAddressEntity());
        Employee employee = employeeService.getEmployeeById("123");
        Assert.assertNotNull(employee);
    }

    @Test
    public void retrieveIfRecordMissing() {

        Mockito.when(employeeDao.findByEmpId(Mockito.any())).thenReturn(Optional.empty());
        Employee employee = employeeService.getEmployeeById("123");
        Assert.assertNull(employee);
    }

}
