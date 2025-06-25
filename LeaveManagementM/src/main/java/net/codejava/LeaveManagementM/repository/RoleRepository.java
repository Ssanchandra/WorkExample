// RoleRepository.java
package net.codejava.LeaveManagementM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.LeaveManagementM.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
