package net.codejava.service;

import java.util.List;

import net.codejava.model.Leave;
import net.codejava.model.LeaveRequestentity;

public interface LeaveService {
    Leave applyLeave(Long employeeId, Leave leave);
    LeaveRequestentity approveLeave(Long leaveId, boolean approved);
    List<Leave> getLeaveHistory(Long employeeId);
    Leave saveLeaveRequest(Leave request);
    List<Leave> getAllLeaves();
    List<Leave> getLeavesByEmployeeId(Long empId);
}
