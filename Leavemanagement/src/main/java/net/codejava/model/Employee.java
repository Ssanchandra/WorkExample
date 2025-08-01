package net.codejava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String department;

    // Constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

	public int getSickLeaveBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSickLeaveBalance(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getEarnedLeaveBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setEarnedLeaveBalance(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getCasualLeaveBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCasualLeaveBalance(int i) {
		// TODO Auto-generated method stub
		
	}
}
