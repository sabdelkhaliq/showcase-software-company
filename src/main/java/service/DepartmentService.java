package service;

import exceptions.BusinessException;
import model.Department;

import java.util.HashMap;
import java.util.Map;

public class DepartmentService {
    private Map<Integer, Department> departments;

    private static class DepartmentServiceSingleton {
        private static final DepartmentService INSTANCE = new DepartmentService();
    }

    public static DepartmentService getInstance() {
        return DepartmentServiceSingleton.INSTANCE;
    }

    private DepartmentService() {
        departments = new HashMap<>();
    }

    public void createDepartment(Department department) throws BusinessException {
        validateDepartmentNotNull(department);
        validateDepartmentIdNotRepeated(department.getId());

        departments.put(department.getId(), department);
    }

    public void updateDepartment(Department department) {
        validateDepartmentNotNull(department);
        int departmentId = department.getId();
        validateDepartmentIdFound(departmentId);

        departments.put(departmentId, department);
    }

    private void validateDepartmentNotNull(Department department) throws BusinessException {
        if (department == null)
            throw new BusinessException("Department should not be null");
    }

    private void validateDepartmentIdNotRepeated(int departmentId) {
        boolean departmentIdRepeated = departments.values().stream().anyMatch(e -> e.getId() == departmentId);
        if (departmentIdRepeated) {
            throw new BusinessException("Department id is repeated");
        }
    }

    public void validateDepartmentIdFound(int departmentId) {
        if (!departments.containsKey(departmentId))
            throw new BusinessException("Department id wasn't found");
    }

    public Map<Integer, Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<Integer, Department> departments) {
        this.departments = departments;
    }
}
