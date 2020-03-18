package service;

import exceptions.BusinessException;
import model.Department;
import model.Bug;
import model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BugServiceTest {

    @Before
    public void setup() {
        BugService.getInstance().setBugs(new HashMap<>());
        EmployeeService.getInstance().setEmployees(new HashMap<>());
    }

    //--------- Create Bug Test
    @Test
    public void createBugTest() {
        Bug validBug = new Bug(1, "Severe Bug #1", null);
        BugService.getInstance().createBug(validBug);
        assertEquals(new Bug(1, "Severe Bug #1", null), BugService.getInstance().getBugs().get(1));
    }

    @Test(expected = BusinessException.class)
    public void createNullBugTest() {
        BugService.getInstance().createBug(null);
    }

    @Test(expected = BusinessException.class)
    public void createRepeatedBugTest() {
        Employee employee = new Employee(1, "Ahmed",null);
        EmployeeService.getInstance().createEmployee(employee);
        Bug bug = new Bug(1, "Severe Bug #1", 1);
        BugService.getInstance().createBug(bug);
        Bug repeatedBug = new Bug(1, "Severe Bug #2", 1);
        BugService.getInstance().createBug(repeatedBug);
    }

    @Test(expected = BusinessException.class)
    public void createBugInNotFoundEmployeeTest() {
        Bug bug = new Bug(1, "Severe Bug #1", 1);
        BugService.getInstance().createBug(bug);
    }

    //--------- Update Bug Test
    @Test
    public void updateBugTest() {
        Bug bug = new Bug(1, "Severe Bug #1", null);
        BugService.getInstance().getBugs().put(1, bug);
        bug.setDescription("Moderate Bug #1");
        BugService.getInstance().updateBug(bug);
        assertEquals(new Bug(1, "Moderate Bug #1", null), BugService.getInstance().getBugs().get(1));
    }

    @Test(expected = BusinessException.class)//this test is redundant
    public void updateNullBugTest() {
        BugService.getInstance().updateBug(null);
    }

    @Test(expected = BusinessException.class)
    public void updateNotFoundBugTest() {
        Bug notFoundBug = new Bug(1, "Severe Bug #1", null);
        BugService.getInstance().updateBug(notFoundBug);
    }

    @Test(expected = BusinessException.class)
    public void updateBugWithNotFoundUserTest() {
        Bug bug = new Bug(1, "Severe Bug #1", null);
        BugService.getInstance().getBugs().put(1, bug);
        bug.setUserId(1);
        BugService.getInstance().updateBug(bug);
    }

    //---------- Assign To User Test
    @Test
    public void assignToUserTest() {
        Employee employee = new Employee(1, "Ahmed",null);
        EmployeeService.getInstance().createEmployee(employee);

        Bug bug = new Bug(1, "Severe Bug #1", null);
        BugService.getInstance().getBugs().put(1, bug);

        BugService.getInstance().assignToUser(1, 1);
    }

}
