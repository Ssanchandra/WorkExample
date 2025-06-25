package net.codejava.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int numberOfDays;

	public int getNumberOfDays() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setStatus(LeaveStatus pending) {
		// TODO Auto-generated method stub
		
	}

	public void setEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
	}

	public void setType(Object type2) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(Object startDate2) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(Object endDate2) {
		// TODO Auto-generated method stub
		
	}

	public void setNumberOfDays(Object numberOfDays2) {
		// TODO Auto-generated method stub
		
	}

	public void setReason(Object reason2) {
		// TODO Auto-generated method stub
		
	}

    // Getters and Setters
}
