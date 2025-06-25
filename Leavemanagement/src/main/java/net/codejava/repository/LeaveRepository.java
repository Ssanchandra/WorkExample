package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Leave;
import net.codejava.model.LeaveRequestentity;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequestentity, Long> {
    List<Leave> findByEmployeeId(Long employeeId);

	Leave save(Leave leave);
}
