package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.RoleDto;
import edu.challengAlkemy.javaSpringBoot.entity.Role;

public interface RoleService {
    
    Role getRoleById(Long id);
    Role createRole(RoleDto dto);
    void deleteRole(Long id);
}
