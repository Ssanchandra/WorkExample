package net.codejava.LeaveManagementM.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import net.codejava.LeaveManagementM.entity.Role;
import net.codejava.LeaveManagementM.service.RoleService;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Role addRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @GetMapping("/getAllRoles")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }

    @GetMapping("/getRoleById/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        roleService.save(role);
        return ResponseEntity.ok("Role updated successfully!");
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseEntity.ok("Role deleted successfully!");
    }
}
