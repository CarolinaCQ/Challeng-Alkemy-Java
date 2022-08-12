package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.UserDto;
import edu.challengAlkemy.javaSpringBoot.entity.User;
import edu.challengAlkemy.javaSpringBoot.service.RoleServiceImpl;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoToUser implements Function<UserDto, User>{
    
    private final BCryptPasswordEncoder encode;
    private final RoleServiceImpl roleService;

    @Override
    public User apply(UserDto dto) {
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encode.encode(dto.getPassword()));
        user.setRoles(roleService.addRoleToUser(dto.getListRolesId()));
        user.setDelete(false);
        
        return user;
    }
    
    
    
}
