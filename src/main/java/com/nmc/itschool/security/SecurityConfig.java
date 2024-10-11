//package com.nmc.itschool.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(
//                        "/user/login",
//                        "/user/register",
//                        "/css/**"
//                ).permitAll() // Allow access to login, registration, and static resources
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/user/login")
//                    .defaultSuccessUrl("/", true)
//                    .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll()
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/login?sessionExpired=true")
//                .maximumSessions(1);
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//}