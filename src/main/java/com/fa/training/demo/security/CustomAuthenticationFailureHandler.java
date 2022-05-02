package com.fa.training.demo.security;

import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserAccount userAccount = userAccountService.findByEmail(email);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
        if (userAccount == null) {
            request.setAttribute("message", "The Username is not found!");
        } else if (!userAccount.isActivated()) {
            request.setAttribute("message", "Your account is deactivated! Please contact your manager!");
        } else if (!passwordEncoder.matches(password, userAccount.getPassword())) {
            request.setAttribute("message", "The password is not correct!");
        }
        requestDispatcher.forward(request, response);
    }
}
