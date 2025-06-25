package net.codejava.LeaveManagementM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.LeaveManagementM.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    
    List<LeaveRequest> findTop5ByOrderByAppliedDateDesc();

   
    List<LeaveRequest> findByEmployeeId(Long employeeId);

   
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, String status);
}
