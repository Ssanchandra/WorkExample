package net.codejava.model;

import net.codejava.DTO.LeaveRequestDTO;

public class LeaveMapper {
    public static Leave toEntity(LeaveRequestDTO dto) {
        Leave leave = new Leave();
        leave.setType(dto.getType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setNumberOfDays(dto.getNumberOfDays());
        leave.setReason(dto.getReason());
        return leave;
    }
}
