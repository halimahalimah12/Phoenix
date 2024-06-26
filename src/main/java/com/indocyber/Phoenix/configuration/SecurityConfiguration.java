package com.indocyber.Phoenix.configuration;

import com.indocyber.Phoenix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request ->
                        request.requestMatchers("/resources/**","/error/403","/rooms/**","/roomService","/roomService/**","/home").permitAll()
                                .requestMatchers("/register/**", "/login").anonymous()
                                .requestMatchers("/admin").hasAuthority("Administrator")
                                .requestMatchers("/inventory").hasAuthority("Administrator")
                                .requestMatchers("/reservation").hasAuthority("Administrator")
                                .requestMatchers("/booking").hasAuthority("Guest")
                                .anyRequest().authenticated()
                ).formLogin(login->login //login postnya kemanadimana dan kemana
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticating")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/home",true))
                .logout(logout ->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true"));
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
