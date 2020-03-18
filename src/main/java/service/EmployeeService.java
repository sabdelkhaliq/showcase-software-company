package service;

import model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class EmployeeService {
    private Map<Integer, Employee> employees;
    private static int numberOfEmployees;

    private static class EmployeeServiceSingleton {
        private static final EmployeeService INSTANCE = new EmployeeService();
    }

    public static EmployeeService getInstance() {
        return EmployeeServiceSingleton.INSTANCE;
    }

    private EmployeeService() {
        employees = new HashMap<Integer, Employee>();
    }

    public void createEmployee(Employee employee) {
        int employeeKey = numberOfEmployees + 1;
        employees.put(employeeKey, employee);
    }

    public void updateEmployee(Employee employee) {
        int employeeKey = employee.getId();
        if (employees.containsKey(employeeKey)) {
            employees.put(employeeKey, employee);
        }
    }

    public void assignToDepartment(int employeeId, int departmentId) {
        if (employees.containsKey(employeeId)) {
            Employee employee = employees.get(employeeId);
            employee.setDepatmentId(departmentId);
            employees.put(employeeId, employee);
        }
    }

    public List<Employee> getAllEmployees(final int departmentId) {
        return employees.entrySet().stream().filter(e -> e.getValue().getDepatmentId() == departmentId).map(e -> e.getValue()).collect(toList());
    }
}
