package service;

import exceptions.BusinessException;
import model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class EmployeeService {
    private Map<Integer, Employee> employees;

    private static class EmployeeServiceSingleton {
        private static final EmployeeService INSTANCE = new EmployeeService();
    }

    public static EmployeeService getInstance() {
        return EmployeeServiceSingleton.INSTANCE;
    }

    private EmployeeService() {
        employees = new HashMap<>();
    }

    public void createEmployee(Employee employee) {
        validateEmployeeNotNull(employee);
        validateEmployeeIdNotRepeated(employee.getId());
        if (employee.getDepartmentId() != null)
            DepartmentService.getInstance().validateDepartmentIdFound(employee.getDepartmentId());

        employees.put(employee.getId(), employee);
    }

    public void updateEmployee(Employee employee) {
        validateEmployeeNotNull(employee);
        int employeeId = employee.getId();
        validateEmployeeIdFound(employeeId);
        if (employee.getDepartmentId() != null)
            DepartmentService.getInstance().validateDepartmentIdFound(employee.getDepartmentId());
        employees.put(employeeId, employee);
    }

    public void assignToDepartment(int employeeId, int departmentId) {
        validateEmployeeIdFound(employeeId);
        DepartmentService.getInstance().validateDepartmentIdFound(departmentId);

        Employee employee = employees.get(employeeId);
        employee.setDepartmentId(departmentId);
        employees.put(employeeId, employee);
    }

    public List<Employee> getAllEmployees() {
        return employees.values().stream().collect(toList());
    }

    private void validateEmployeeNotNull(Employee employee) {
        if (employee == null)
            throw new BusinessException("Employee should not be null");
    }

    private void validateEmployeeIdNotRepeated(int employeeId) {
        boolean employeeIdRepeated = employees.values().stream().anyMatch(e -> e.getId() == employeeId);
        if (employeeIdRepeated) {
            throw new BusinessException("Employee id is repeated");
        }
    }

    private void validateEmployeeIdFound(int employeeId) {
        if (!employees.containsKey(employeeId))
            throw new BusinessException("Department id wasn't found");
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }
}
