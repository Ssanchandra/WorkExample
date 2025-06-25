package net.codejava.LeaveManagementM.service;

import java.util.List;

import net.codejava.LeaveManagementM.entity.LeaveRequest;

public interface LeaveRequestService {
	List<LeaveRequest> getAllLeaves();
	LeaveRequest applyLeave(LeaveRequest request);
	LeaveRequest changeStatus(Long id, String status);
	LeaveRequest getLeaveById(Long id);
	List<LeaveRequest> getRecentLeaves();
	List<LeaveRequest> getLeavesByEmployeeId(Long employeeId);
	int getLeaveBalance(Long employeeId);
	void deleteLeaveById(Long id);
	List<LeaveRequest> filterLeavesByMonthAndYear(int month, int year);

}
