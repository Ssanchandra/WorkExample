package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.model.Employee;
import net.codejava.model.Leave;
import net.codejava.model.LeaveRequestentity;
import net.codejava.model.LeaveStatus;
import net.codejava.repository.EmployeeRepository;
import net.codejava.repository.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

    private static final int CASUAL = 0;

	private static final int SICK = 0;

	@Autowired
    private LeaveRepository leaveRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public Leave applyLeave(Long employeeId, Leave leave) {
        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        int days = leave.getNumberOfDays();

        switch (leave.getType()) {
            case SICK:
                if (emp.getSickLeaveBalance() < days)
                    throw new RuntimeException("Insufficient Sick Leave balance.");
                emp.setSickLeaveBalance(emp.getSickLeaveBalance() - days);
                break;

            case CASUAL:
                if (emp.getCasualLeaveBalance() < days)
                    throw new RuntimeException("Insufficient Casual Leave balance.");
                emp.setCasualLeaveBalance(emp.getCasualLeaveBalance() - days);
                break;

            case EARNED:
                if (emp.getEarnedLeaveBalance() < days)
                    throw new RuntimeException("Insufficient Earned Leave balance.");
                emp.setEarnedLeaveBalance(emp.getEarnedLeaveBalance() - days);
                break;

            default:
                throw new RuntimeException("Invalid leave type.");
        }

        leave.setEmployee(emp);
        leave.setStatus(LeaveStatus.PENDING);

        employeeRepo.save(emp); // Save updated balances
        return leaveRepo.save(leave);
    }

    @Override
    public LeaveRequestentity approveLeave(Long leaveId, boolean approved) {
        LeaveRequestentity leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(approved ? LeaveStatus.APPROVED : LeaveStatus.REJECTED);
        return leaveRepo.save(leave);
    }

    @Override
    public List<Leave> getLeaveHistory(Long employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }

    @Override
    public Leave saveLeaveRequest(Leave request) {
        return leaveRepo.save(request);
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepo.findAll();
    }

    @Override
    public List<Leave> getLeavesByEmployeeId(Long empId) {
        return leaveRepo.findByEmployeeId(empId);
    }
}
