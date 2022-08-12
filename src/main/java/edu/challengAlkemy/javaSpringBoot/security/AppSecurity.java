package edu.challengAlkemy.javaSpringBoot.security;

import edu.challengAlkemy.javaSpringBoot.filter.CustomAuthenticationFilter;
import edu.challengAlkemy.javaSpringBoot.filter.CustomAuthorizationFilter;
import edu.challengAlkemy.javaSpringBoot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurity extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/genres/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/roles/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/characters/create/**", "/characters/update/**", "/characters/delete/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/movies/create/**", "/movies/update/**", "/movies/delete/**", "/movies/{idMovie}/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
