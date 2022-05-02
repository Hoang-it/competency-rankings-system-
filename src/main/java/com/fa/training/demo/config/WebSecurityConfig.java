package com.fa.training.demo.config;

import com.fa.training.demo.security.*;
import com.fa.training.demo.service.RoleService;
import com.fa.training.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomOidcUserService oidcUserService;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    // Using for verify google reCaptcha v2
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/welcome","/login","/register").permitAll()
                .antMatchers("/login-success","/employee/**").hasRole("MEMBER")
                .antMatchers("/manager/**").hasAnyRole("MANAGER", "LEADER")
                .antMatchers("/profile-patterns/**").hasRole("MANAGER")
                .antMatchers("/administration/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/log-out")
                .and()
                .rememberMe().tokenValiditySeconds(3600)
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .permitAll()
                    .userInfoEndpoint()
                        .userService(oAuth2UserService)
                        .oidcUserService(oidcUserService)
                    .and()
                    .successHandler(authenticationSuccessHandler);
    }
}
