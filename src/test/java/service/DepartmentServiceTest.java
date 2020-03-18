package service;

import exceptions.BusinessException;
import model.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DepartmentServiceTest {

    @Before
    public void setup()
    {
        DepartmentService.getInstance().setDepartments(new HashMap<>());
    }

    //--------- Create Department Test
    @Test
    public void createDepartmentTest() {
        Department validDepartment = new Department(1, "IT");
        DepartmentService.getInstance().createDepartment(validDepartment);
        assertEquals(new Department(1, "IT"), DepartmentService.getInstance().getDepartments().get(1));
    }

    @Test(expected = BusinessException.class)
    public void createNullDepartmentTest() {
        DepartmentService.getInstance().createDepartment(null);
    }

    @Test(expected = BusinessException.class)
    public void createRepeatedDepartmentTest() {
        Department department = new Department(2, "L&D");
        DepartmentService.getInstance().createDepartment(department);
        Department repeatedDepartment = new Department(2, "English");
        DepartmentService.getInstance().createDepartment(repeatedDepartment);
    }

    //--------- Update Department Test
    @Test
    public void updateDepartmentTest() {
        Department department = new Department(3, "Delivery");
        DepartmentService.getInstance().getDepartments().put(3, department);
        department.setName("DevOps");
        DepartmentService.getInstance().updateDepartment(department);
        assertEquals(new Department(3, "DevOps"), DepartmentService.getInstance().getDepartments().get(3));
    }

    @Test(expected = BusinessException.class)
    public void updateNullDepartmentTest() {//this test is redundant
        DepartmentService.getInstance().updateDepartment(null);
    }

    @Test(expected = BusinessException.class)
    public void updateNotFoundDepartmentTest() {
        Department notFoundDepartment = new Department(6, "Java");
        DepartmentService.getInstance().updateDepartment(notFoundDepartment);
    }
}