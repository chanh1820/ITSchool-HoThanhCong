package com.nmc.itschool.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setDefaultTargetUrl("/home/index");
//        http.authorizeRequests()
//                .antMatchers("/home/index").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                    .loginPage("/user/login")
//                    .usernameParameter("userName")
//                    .defaultSuccessUrl("/home/index")
//                    .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/").permitAll();
        http
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()

                .authorizeRequests()
                .antMatchers("/api/**","/api/subject").authenticated()
                .antMatchers(
                        "/user/login",
                        "/user/register",
                        "/user/process_register",
                        "/css/*",
                        "/css/lib/*",
                        "/js/*",
                        "/images/*",
                        "/resource/*",
                        "/home/*",
                        "/*",
                        "*",
                        "/lesson/*"
                )
                .permitAll() // Allow access to login, registration, and static resources
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/home/index")
//                    .successHandler(successHandler) // Custom success handler with fallback URL
                    .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }


}
