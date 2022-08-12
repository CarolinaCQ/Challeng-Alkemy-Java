package edu.challengAlkemy.javaSpringBoot.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.challengAlkemy.javaSpringBoot.dto.UserDto;
import edu.challengAlkemy.javaSpringBoot.entity.Role;
import edu.challengAlkemy.javaSpringBoot.entity.User;
import edu.challengAlkemy.javaSpringBoot.service.UserServiceImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(response.getContentType());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
