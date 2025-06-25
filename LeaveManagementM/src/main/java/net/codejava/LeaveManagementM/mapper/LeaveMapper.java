package net.codejava.LeaveManagementM.mapper;

import net.codejava.LeaveManagementM.dto.LeaveRequestDTO;
import net.codejava.LeaveManagementM.dto.LeaveResponseDTO;
import net.codejava.LeaveManagementM.entity.Employee;
import net.codejava.LeaveManagementM.entity.LeaveRequest;

public class LeaveMapper {

    public static LeaveRequest toEntity(LeaveRequestDTO dto, Employee employee) {
        LeaveRequest entity = new LeaveRequest();
        entity.setEmployee(employee);
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setType(dto.getType());
        entity.setReason(dto.getReason());
        return entity;
    }

    public static LeaveResponseDTO toDTO(LeaveRequest entity) {
        LeaveResponseDTO dto = new LeaveResponseDTO();
        dto.setLeaveId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setEmployeeName(entity.getEmployee().getName());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setType(entity.getType());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        dto.setAppliedDate(entity.getAppliedDate());
        return dto;
    }
}
