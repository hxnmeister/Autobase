package com.ua.project.Autobase.configuration;

import com.ua.project.Autobase.services.implementations.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImp userDetailsService;
    private final MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().mvcMatchers("/", "login", "/logout").permitAll();
        httpSecurity.authorizeRequests().antMatchers(
                "/cars/create",
                "cars/insert",
                "/drivers/create",
                "drivers/insert",
                "/routes/create",
                "/routes/set-routes",
                "/applications/create",
                "/applications/insert").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DISPATCHER')");
        httpSecurity.authorizeRequests().mvcMatchers(
                "/cars/get-all",
                "/drivers/get-all",
                "/routes/get-all",
                "/applications/get-all").access("hasAnyRole('ROLE_DRIVER')");

        httpSecurity.authorizeRequests().and().exceptionHandling().authenticationEntryPoint(myBasicAuthenticationEntryPoint);

        httpSecurity.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/auth/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success");
    }
}
