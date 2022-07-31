package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.RoleDto;
import edu.challengAlkemy.javaSpringBoot.entity.Role;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoToRole implements Function<RoleDto, Role> {

    @Override
    public Role apply(RoleDto dto) {
        
        Role role = new Role();
        role.setName(dto.getName());
        role.setDelete(false);
        
        return role;
    }

}
