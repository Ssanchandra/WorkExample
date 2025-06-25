package net.codejava.LeaveManagementM.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.LeaveManagementM.entity.LeaveRequest;
import net.codejava.LeaveManagementM.repository.EmployeeRepository;
import net.codejava.LeaveManagementM.repository.LeaveRequestRepository;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // ✅ Apply Leave
    @Override
    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus("Pending");
        request.setAppliedDate(LocalDate.now());
        return leaveRequestRepository.save(request);
    }

    // ✅ Change Leave Status
    @Override
    public LeaveRequest changeStatus(Long id, String status) {
        LeaveRequest leave = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found with ID: " + id));
        leave.setStatus(status);
        return leaveRequestRepository.save(leave);
    }

    // ✅ Get Leave By ID
    @Override
    public LeaveRequest getLeaveById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found with ID: " + id));
    }

    // ✅ Get Top 5 Recent Leaves
    @Override
    public List<LeaveRequest> getRecentLeaves() {
        return leaveRequestRepository.findTop5ByOrderByAppliedDateDesc();
    } 
    //✅ Filter Leaves by Month and Year
    @Override
    public List<LeaveRequest> filterLeavesByMonthAndYear(int month, int year) {
        return leaveRequestRepository.findAll().stream()
                .filter(lr -> {
                    LocalDate start = lr.getStartDate();
                    return start != null && start.getMonthValue() == month && start.getYear() == year;
                })
                .collect(Collectors.toList());
    }

    // ✅ Get All Leaves
    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    // ✅ Get Leaves by Employee ID
    @Override
    public List<LeaveRequest> getLeavesByEmployeeId(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    // ✅ Get Leave Balance
    @Override
    public int getLeaveBalance(Long employeeId) {
        List<LeaveRequest> approvedLeaves = leaveRequestRepository.findByEmployeeIdAndStatus(employeeId, "Approved");

        int totalDays = approvedLeaves.stream()
                .mapToInt(leave -> {
                    long days = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate());
                    return (int) days + 1;
                })
                .sum();

        int maxAllowedLeaves = 30; // Customize based on your leave policy
        return Math.max(0, maxAllowedLeaves - totalDays);
    }

    // ✅ Delete Leave by ID
    @Override
    public void deleteLeaveById(Long id) {
        if (!leaveRequestRepository.existsById(id)) {
            throw new RuntimeException("Leave request not found with ID: " + id);
        }
        leaveRequestRepository.deleteById(id);
    }
}
