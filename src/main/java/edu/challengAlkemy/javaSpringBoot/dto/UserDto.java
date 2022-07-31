package edu.challengAlkemy.javaSpringBoot.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    
    private String username;
    
    private String password;
    
    private List<Long> listRolesId;
}
