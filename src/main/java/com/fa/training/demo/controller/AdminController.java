package com.fa.training.demo.controller;


import com.fa.training.demo.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/administration")
public class AdminController {

    /**
     * show Admin page after login successfully and the correct role
     */
    @GetMapping("")
    public String showAdminPage(Principal principal) {
        UserDetails admin = (UserDetails) ((Authentication) principal).getPrincipal();
        System.out.println(admin);
        return "administration/administration";
    }

}
