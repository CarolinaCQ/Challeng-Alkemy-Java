package edu.challengAlkemy.javaSpringBoot.controller;

import edu.challengAlkemy.javaSpringBoot.dto.UserDto;
import edu.challengAlkemy.javaSpringBoot.entity.User;
import edu.challengAlkemy.javaSpringBoot.service.EmailService;
import edu.challengAlkemy.javaSpringBoot.service.UserServiceImpl;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(response.getContentType());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto) {
        User user = userService.createUser(dto);
        emailService.sendMail(dto.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
