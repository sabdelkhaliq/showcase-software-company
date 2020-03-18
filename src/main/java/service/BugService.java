package service;

import exceptions.BusinessException;
import model.Bug;
import model.Bug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BugService {

    private Map<Integer, Bug> bugs;

    private static class BugServiceSingleton {
        private static final BugService INSTANCE = new BugService();
    }

    public static BugService getInstance() {
        return BugService.BugServiceSingleton.INSTANCE;
    }

    private BugService() {
        bugs = new HashMap<>();
    }

    public void createBug(Bug bug) throws BusinessException {
        validateBugNotNull(bug);
        validateBugIdNotRepeated(bug.getId());
        Integer userId = bug.getUserId();
        if (userId != null)
            EmployeeService.getInstance().validateEmployeeIdFound(userId);
        bugs.put(bug.getId(), bug);
    }

    public void updateBug(Bug bug) {
        validateBugNotNull(bug);
        int bugId = bug.getId();
        validateBugIdFound(bugId);
        Integer userId = bug.getUserId();
        if (userId != null)
            EmployeeService.getInstance().validateEmployeeIdFound(userId);
        bugs.put(bugId, bug);
    }

    public void assignToUser(int bugId, int employeeId) {
        validateBugIdFound(bugId);
        EmployeeService.getInstance().validateEmployeeIdFound(employeeId);

        Bug bug = bugs.get(bugId);
        bug.setUserId(employeeId);
        bugs.put(bugId, bug);
    }

    public List<Bug> getAllBugs() {
        return bugs.values().stream().collect(toList());
    }

    private void validateBugIdFound(int bugId) {
        if (!bugs.containsKey(bugId))
            throw new BusinessException("Bug id wasn't found");
    }

    private void validateBugNotNull(Bug bug) {
        if (bug == null)
            throw new BusinessException("Bug should not be null");
    }

    private void validateBugIdNotRepeated(int bugId) {
        boolean bugIdRepeated = bugs.values().stream().anyMatch(e -> e.getId() == bugId);
        if (bugIdRepeated) {
            throw new BusinessException("Bug id is repeated");
        }
    }

    public Map<Integer, Bug> getBugs() {
        return bugs;
    }

    public void setBugs(Map<Integer, Bug> bugs) {
        this.bugs = bugs;
    }
}
