package edu.challengAlkemy.javaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class JavaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootApplication.class, args);
    }
      
}
