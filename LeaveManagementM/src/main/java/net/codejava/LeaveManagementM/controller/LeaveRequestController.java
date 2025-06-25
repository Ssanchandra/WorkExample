package net.codejava.LeaveManagementM.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.codejava.LeaveManagementM.dto.LeaveRequestDTO;
import net.codejava.LeaveManagementM.dto.LeaveResponseDTO;
import net.codejava.LeaveManagementM.entity.Employee;
import net.codejava.LeaveManagementM.entity.LeaveRequest;
import net.codejava.LeaveManagementM.mapper.LeaveMapper;
import net.codejava.LeaveManagementM.repository.EmployeeRepository;
import net.codejava.LeaveManagementM.service.LeaveRequestService;

@RestController
@RequestMapping("/leaves")
@CrossOrigin
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService service;

    @Autowired
    private EmployeeRepository employeeRepo;

    // ✅ Apply for Leave
    @PostMapping("/apply")
    public ResponseEntity<LeaveResponseDTO> applyLeave(@Valid @RequestBody LeaveRequestDTO dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + dto.getEmployeeId()));

        LeaveRequest entity = LeaveMapper.toEntity(dto, employee);
        LeaveRequest saved = service.applyLeave(entity);
        LeaveResponseDTO response = LeaveMapper.toDTO(saved);
        return ResponseEntity.ok(response);
    }

    // ✅ Update Leave Status (Approve / Reject)
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        service.changeStatus(id, status);
        return ResponseEntity.ok("Leave status updated successfully!");
    }

    // ✅ Delete Leave
    @DeleteMapping("/deleteLeaveById/{id}")
    public ResponseEntity<String> deleteLeave(@PathVariable Long id) {
        service.deleteLeaveById(id);
        return ResponseEntity.ok("Leave deleted successfully!");
    }

    
    // ✅ Get Top 5 Recent Leaves
    @GetMapping("/getRecentLeaves")
    public ResponseEntity<List<LeaveResponseDTO>> getRecentLeaves() {
        List<LeaveResponseDTO> list = service.getRecentLeaves().stream()
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
 // ✅ Get All Leaves
    @GetMapping("/getAll")
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaves() {
        List<LeaveResponseDTO> list = service.getAllLeaves().stream()
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ✅ Filter Leaves by Month and Year
    @GetMapping("/filterByMonthYear")
    public ResponseEntity<List<LeaveResponseDTO>> filterLeavesByMonthYear(
            @RequestParam int month,
            @RequestParam int year) {
        List<LeaveResponseDTO> filtered = service.getAllLeaves().stream()
                .filter(lr -> {
                    LocalDate start = lr.getStartDate();
                    return start.getMonthValue() == month && start.getYear() == year;
                })
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    // ✅ Get Leaves by Employee
    @GetMapping("/getLeavesByEmployeeId/{employeeId}")
    public ResponseEntity<List<LeaveResponseDTO>> getLeavesByEmployee(@PathVariable Long employeeId) {
        List<LeaveResponseDTO> list = service.getLeavesByEmployeeId(employeeId).stream()
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ✅ Get Leave Balance
    @GetMapping("/getLeaveBalance/{employeeId}")
    public ResponseEntity<Integer> getLeaveBalance(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getLeaveBalance(employeeId));
    }
}
