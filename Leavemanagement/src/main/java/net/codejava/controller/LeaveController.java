package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.DTO.LeaveRequestDTO;
import net.codejava.model.Leave;
import net.codejava.model.LeaveRequestentity;
import net.codejava.service.LeaveService;

@RestController
@RequestMapping("/Leavemanagement/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    
    @PostMapping("/apply/{employeeId}")
    public ResponseEntity<Leave> applyLeave(@PathVariable Long employeeId, @RequestBody Leave leave) {
        Leave appliedLeave = leaveService.applyLeave(employeeId, leave);
        return ResponseEntity.ok(appliedLeave);
    }
    @PostMapping("/apply/{employeeId}")
    public ResponseEntity<Leave> applyLeave(@PathVariable Long employeeId, @RequestBody LeaveRequestDTO dto) {
        Leave leave = new Leave();
        leave.setType(dto.getType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setNumberOfDays(dto.getNumberOfDays());
        leave.setReason(dto.getReason());

        Leave appliedLeave = leaveService.applyLeave(employeeId, leave);
        return ResponseEntity.ok(appliedLeave);
    }

   
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveRequestentity> approveLeave(@PathVariable Long leaveId, @RequestParam boolean approved) {
        LeaveRequestentity updatedLeave = leaveService.approveLeave(leaveId, approved);
        return ResponseEntity.ok(updatedLeave);
    }

   
    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<Leave>> getLeaveHistory(@PathVariable Long employeeId) {
        List<Leave> history = leaveService.getLeaveHistory(employeeId);
        return ResponseEntity.ok(history);
    }
}
