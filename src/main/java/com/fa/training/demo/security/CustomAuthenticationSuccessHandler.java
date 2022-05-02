package com.fa.training.demo.security;

import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/administration");
        } else if (roles.contains("ROLE_MANAGER") || roles.contains("ROLE_LEADER")) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            UserAccount userAccount = userAccountService.findByEmail(user.getUsername());
            userAccount.setLastLogged(LocalDate.now());
            System.out.println("Check manager roles");
            userAccountService.save(userAccount);
            httpServletResponse.sendRedirect("/manager");
        } else if (roles.contains("ROLE_MEMBER")) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            UserAccount userAccount = userAccountService.findByEmail(user.getUsername());
            userAccount.setLastLogged(LocalDate.now());
            System.out.println("check employee roles");
            userAccountService.save(userAccount);
            httpServletResponse.sendRedirect("/employee");
        }
    }
}
