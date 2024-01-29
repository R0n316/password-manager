package ru.alex.FisrtSecurityApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.alex.FisrtSecurityApp.services.PersonDetailsService;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(personDetailsService)
        ;

        http.authorizeHttpRequests(req -> req.requestMatchers("/people/**").hasRole("ADMIN"));
        http.authorizeHttpRequests(req -> req.requestMatchers("/static/**","/auth/registration").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN"));
        http.formLogin(form -> form.loginPage("/auth/login").permitAll().loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/web-info",true)
                .failureUrl("/auth/login?error")
        );
        http.logout(session -> session.logoutUrl("/logout").logoutSuccessUrl("/auth/login"));

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(personDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}