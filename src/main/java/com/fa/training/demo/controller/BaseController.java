package com.fa.training.demo.controller;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.service.EmployeeService;
import com.fa.training.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Initialize userDetails information for all employee Pages
     */
    @ModelAttribute("userDetails")
    public UserDetails initUser(Principal principal) {
        return (UserDetails) ((Authentication) principal).getPrincipal();
    }

    /**
     * Initialize userAccount information for all employee Pages
     */
    @ModelAttribute("userAccount")
    public UserAccount initUserAccount(@ModelAttribute("userDetails") UserDetails userDetails) {
        return userAccountService.findByEmail(userDetails.getUsername());
    }

    /**
     * Initialize employee information for all employee Pages
     */
    @ModelAttribute("employee")
    public Employee initEmployee(@ModelAttribute("userAccount") UserAccount userAccount) {
        return employeeService.getEmployee(userAccount.getEmployee().getEmployeeId());
    }

    /**
     * Initialize employee contact for all employee Pages
     */
    @ModelAttribute("employeeContact")
    public EmployeeContact initContact(@ModelAttribute("employee") Employee employee) {
        return employeeService.getEmployeeContact(employee);
    }

}
