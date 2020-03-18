package service;

import exceptions.BusinessException;
import model.Department;
import model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceTest {

    @Before
    public void setup()
    {
        EmployeeService.getInstance().setEmployees(new HashMap<>());
        DepartmentService.getInstance().setDepartments(new HashMap<>());
    }

    //--------- Create Employee Test
    @Test
    public void createEmployeeTest() {
        Employee validEmployee = new Employee(1, "Rana", null);
        EmployeeService.getInstance().createEmployee(validEmployee);
        assertEquals(new Employee(1, "Rana", null), EmployeeService.getInstance().getEmployees().get(1));
    }

    @Test(expected = BusinessException.class)
    public void createNullEmployeeTest() {
        EmployeeService.getInstance().createEmployee(null);
    }

    @Test(expected = BusinessException.class)
    public void createRepeatedEmployeeTest() {

        Department department = new Department(7, "IT");
        DepartmentService.getInstance().createDepartment(department);
        Employee employee = new Employee(2, "Dora", 7);
        EmployeeService.getInstance().createEmployee(employee);
        Employee repeatedEmployee = new Employee(2, "Ali", 7);
        EmployeeService.getInstance().createEmployee(repeatedEmployee);
    }

    @Test(expected = BusinessException.class)
    public void createEmployeeInNotFoundDepartmentTest() {
        Employee employee = new Employee(3, "Mona", 5);
        EmployeeService.getInstance().createEmployee(employee);
    }

    //--------- Update Employee Test
    @Test
    public void updateEmployeeTest() {
        Employee employee = new Employee(4, "Ramie", null);
        EmployeeService.getInstance().getEmployees().put(4, employee);
        employee.setName("Ahmed");
        EmployeeService.getInstance().updateEmployee(employee);
        assertEquals(new Employee(4, "Ahmed", null), EmployeeService.getInstance().getEmployees().get(4));
    }

    @Test(expected = BusinessException.class)//this test is redundant
    public void updateNullEmployeeTest() {
        EmployeeService.getInstance().updateEmployee(null);
    }

    @Test(expected = BusinessException.class)
    public void updateNotFoundEmployeeTest() {
        Employee notFoundEmployee = new Employee(5, "Ali", 2);
        EmployeeService.getInstance().updateEmployee(notFoundEmployee);
    }

    @Test(expected = BusinessException.class)
    public void updateEmployeeWithNotFoundDepartmentTest() {
        Employee employee = new Employee(5, "Dina", null);
        EmployeeService.getInstance().getEmployees().put(5, employee);
        employee.setDepartmentId(6);
        EmployeeService.getInstance().updateEmployee(employee);
    }

    //---------- Get All Employees Test
    @Test
    public void getAllEmployeesTest() {
        Employee employee = new Employee(1, "Mahmoud", null);
        EmployeeService.getInstance().getEmployees().put(1, employee);

        assertEquals(1, EmployeeService.getInstance().getAllEmployees().size());
    }

    //---------- Assign To Department Test
    @Test
    public void assignToDepartmentTest()
    {
        Department department = new Department(8, "DevOps");
        DepartmentService.getInstance().createDepartment(department);

        Employee employee = new Employee(1, "Mahmoud", null);
        EmployeeService.getInstance().getEmployees().put(1, employee);

        EmployeeService.getInstance().assignToDepartment(1, 8);
    }

}
