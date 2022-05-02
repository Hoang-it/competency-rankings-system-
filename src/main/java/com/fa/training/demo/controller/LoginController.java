package com.fa.training.demo.controller;


import com.fa.training.demo.entities.*;
import com.fa.training.demo.repository.JobRankRepository;
import com.fa.training.demo.service.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private JobRankService jobRankService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Display logout page after user click Sign-out button
     * Spring security will do it by logoutRequestMatcher
     */
    @GetMapping("/log-out")
    public ModelAndView goToLogoutPage(ModelAndView modelAndView){
        modelAndView.setViewName("logout");
        return modelAndView;
    }

    /**
     * Display login page
     */
    @GetMapping("/login")
    public String goToLoginPage(){
        return "login";
    }

    /**
     * Using for handle Exception by AuthenticationFailureHandler when we're failed to login
     */
    @PostMapping("/login")
    public String loginError(){
        return "login";
    }

    /**
     * display home page
     */
    @GetMapping(value = {"/","/welcome"})
    public String welcome(){
        return "index";
    }

    private FacebookConnectionFactory factoryFacebook = new FacebookConnectionFactory("5940707412609924",
            "38eadb7a0ceebfc699aba9182a49339e");

    /**
     * Show Register Page and add empty user
     */
    @GetMapping("/register")
    public ModelAndView showRegisterPage(UserAccount userAccount) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("userAccount", userAccount);
        return modelAndView;
    }

    /**
     * Use for register new Account
     */
    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("email") String email
            , @RequestParam("fullName") String fullName
            , @RequestParam("phone") String phone
            , @RequestParam("gender") boolean gender
            , @RequestParam("maritalStatus") boolean maritalStatus
            , @RequestParam("g-recaptcha-response") String gRecaptchaResponse
            , UserAccount userAccount) throws MessagingException, UnsupportedEncodingException {

        ModelAndView modelAndView = new ModelAndView();
        UserAccount newUser = userAccountService.findByEmail(email);
        // Check the user account already exists?
        if (newUser != null) {
            modelAndView.addObject("message", "The Email already exists! Please try again!");
            modelAndView.setViewName("register");
            // validate in order to ensure google recaptcha is valid
        } else if (!reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
            modelAndView.addObject("message", "Invalid reCaptcha!");
            modelAndView.setViewName("register");
        } else {
            // Saving information of user Account
            userAccount.setUserName(email);
            Role role =roleService.findByRoleName("ROLE_MEMBER");
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            userAccount.setRoles(roles);
            
            // Saving information of employee
            Employee employee = new Employee();
            String[] split= fullName.split(" ");
            employee.setFirstName(split[0]);
            if (split.length > 1) {
                employee.setLastName(split[1]);
            }
            employee.setGender(gender);
            employee.setMarital(maritalStatus);
            employee.setJobTitle(jobTitleService.findById(1));
            employee.setJobRank(jobRankService.findJobRank(1));
            employeeService.save(employee);

            userAccount.setEmployee(employee);
            userAccountService.save(userAccount);

            // Saving contact of employee
            EmployeeContact employeeContact = new EmployeeContact();
            employeeContact.setTelephone(phone);
            employeeContact.setEmail(email);
            employeeContact.setEmployee(employee);
            employeeService.saveEmployeeContact(employeeContact);

            // Create new token for register account
            Token token = new Token(userAccount);
            tokenService.save(token);
            String content = "Dear sir,<br>"
                    + "<br> Please click the link below to verify your account: <br>"
                    + "<br> <b>http://localhost:8080/confirm-registration?token=" + token.getToken() + "</b><br>"
                    + "<br> Thank you, <br>"
                    + "<br> DEMO COMPLETE!!!";
            // send an email to User's email
            emailSenderService.sendEmail(userAccount.getUserName()
                    , "Congratulation: Register New Account Success!"
                    , content);
            modelAndView.setViewName("complete-register");
        }
        return modelAndView;
    }

    /**
     * When user click on the link via their email
     * Setting activate User account to login
     */
    @GetMapping("/confirm-registration")
    public ModelAndView confirmRegistration(ModelAndView modelAndView
            , @RequestParam("token") String confirmToken) {
        Token token = tokenService.findByToken(confirmToken);
        String page = "error/verify-account";
        // Check token already exist or not yet?
        if (token != null) {
            UserAccount user = userAccountService.findByEmail(token.getUser().getUserName());
            // Validate token to make sure token is always valid
            if (validateToken(token)) {
                user.setActivated(true);
                userAccountService.save(user);
                modelAndView.addObject("token", token.getToken());
                modelAndView.setViewName("confirm-registration");
            } else {
                // Delete Token and User account are expired (Because user didn't confirm activate account)
                tokenService.deleteToken(token);
                // cascade type REMOVE will delete employeeContact and User account which is related
                employeeService.delete(user.getEmployee());
                // userAccountService.deleteUser(user);
                modelAndView.addObject("message", "The link is out of date!");
                modelAndView.setViewName(page);
            }
        } else {
            // Token is invalid, make sure User don't click on the link their confirm via email again
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName(page);
        }
        return modelAndView;
    }

    /**
     * Show forgot password page
     */
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    /**
     * when User has a request forget password, the system will send an email to them.
     */
    @PostMapping("/forgot-password")
    public ModelAndView forgotPassword(ModelAndView modelAndView
            , @RequestParam("g-recaptcha-response") String gRecaptchaResponse
            , @RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        UserAccount user = userAccountService.findByEmail(email);
        Token checkToken = tokenService.findByUser(user);
        String page = "forgot-password";
        // The user is null, its mean the input email of User is not correct!
        if (user != null) {
            // Check token already exists or not yet to create new token for User's request
            if (checkToken == null) {
                // validate in order to ensure google recaptcha is valid
                if (reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
                    Token token = new Token(user);
                    tokenService.save(token);
                    String content = "Dear sir, <br>"
                            + "<br> Please click the link below to change your password: </br>"
                            + "<br><b>http://localhost:8080/set-password?token=" + token.getToken() + "</b><br>"
                            + "<br><b style=\"color:red;\">If it's not you, just ignore this email! </b><br>"
                            + "<br> Thank you, <br>"
                            + "<br> DEMO COMPLETE!!!";
                    // -> Send email to User's email.
                    emailSenderService.sendEmail(email, "Lost Password: A Request change password", content);
                    modelAndView.addObject("user", user);
                    modelAndView.setViewName("recover-password");
                } else {
                    modelAndView.addObject("message", "Invalid reCaptcha!");
                    modelAndView.setViewName(page);
                }
            } else {
                // The token is not null, its mean the user created new account but they haven't activated yet.
                modelAndView.addObject("message", "Please activate your account!");
                modelAndView.setViewName(page);
            }
        } else {
            modelAndView.addObject("message", "The Email is not correct! Please try again!");
            modelAndView.setViewName(page);
        }
        return modelAndView;
    }

    /**
     * the set password page in order to User can change their password
     * The system will delete the token which created by User's request after
     * They click on the link via their email
     */
    @GetMapping("/set-password")
    public ModelAndView setPasswordPage(ModelAndView modelAndView
            , @RequestParam("token") String confirmToken) {
        String page = "error/verify-account";
        Token token = tokenService.findByToken(confirmToken);
        // Make sure the token is not null
        if (token != null) {
            UserAccount user = userAccountService.findByEmail(token.getUser().getUserName());
            // Checking token is valid (not out of date)
            if (validateToken(token)) {
                modelAndView.setViewName("set-password");
                modelAndView.addObject("userId", user.getUserId());
                modelAndView.addObject("token", token.getToken());
            } else {
                // The system will inform a error when they click on the link in their email again
                tokenService.deleteToken(token);
                modelAndView.addObject("message", "The link is out of date!");
                modelAndView.setViewName(page);
            }
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName(page);
        }
        return modelAndView;
    }

    /**
     * Update new password for User account
     */
    @PostMapping("/set-password")
    public ModelAndView setPasswordForAccount(ModelAndView modelAndView
            , @RequestParam("userId") int id
            , @RequestParam("password") String password
            , @RequestParam("token") String token
            , @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
        UserAccount newUser = userAccountService.findById(id);
        Token validToken = tokenService.findByToken(token);
        if (reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
            newUser.setPassword(passwordEncoder.encode(password));
            userAccountService.save(newUser);
            // Delete the token when User has set a password
            // To make sure they will not click on the link in their email again.
            tokenService.deleteToken(validToken);
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("message", "Invalid reCaptcha!");
            setPasswordPage(modelAndView, token);
        }
        return modelAndView;
    }

    /**
     * Return page 403 error when The user is wrong Role.
     */
    @GetMapping("/error/403")
    public String return403Page() {
        return "error/403";
    }

    /**
     * Validate expire token
     * Make sure token is always valid
     */
    private Boolean validateToken(Token token) {
        Date now = new Date();
        if (now.before(token.getExpireDate())) {
            return true;
        }
        return false;
    }
}
