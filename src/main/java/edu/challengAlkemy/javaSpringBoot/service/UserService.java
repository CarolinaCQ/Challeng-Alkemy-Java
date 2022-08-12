package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.UserDto;
import edu.challengAlkemy.javaSpringBoot.entity.User;

public interface UserService {
    
    User getUser(String username);
    User createUser(UserDto dto);
    void deleteUser(Long id);
}
