// RoleService.java
package net.codejava.LeaveManagementM.service;

import java.util.List;

import net.codejava.LeaveManagementM.entity.Role;

public interface RoleService {
    Role save(Role role);
    List<Role> getAll();
    Role getById(Long id);
    void deleteById(Long id);
}
