package com.fa.training.demo.controller;

import com.fa.training.demo.entities.*;
import com.fa.training.demo.repository.*;
import com.fa.training.demo.service.EmployeeService;
import com.fa.training.demo.service.ReCaptchaService;
import com.fa.training.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
@SessionAttributes({"employee", "employeeContact", "userAccount"})
public class EmployeeController extends BaseController{

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PatternRepository patternRepository;

    @Autowired
    DataSourceRepository dataSource;

    /**
     * display Employee page after logged in successfully and the correct role
     */
    @GetMapping("")
    public ModelAndView showEmployeePage(ModelAndView modelAndView) {
        modelAndView.setViewName("employee/employee");
        return modelAndView;
    }

    /**
     * Display create profile Page
     */
    @GetMapping("/new-profile")
    public ModelAndView showNewProfilePage(ModelAndView modelAndView
            , UserDetails userDetails) {
        modelAndView.setViewName("employee/new-profile");
        return modelAndView;
    }

    /**
     * Display create profile Page
     */
    @GetMapping("/profile/edit")
    public ModelAndView showEditPage(ModelAndView modelAndView
            , @RequestParam("id") String id
            , @RequestParam("mode") String mode
            , UserDetails userDetails) {
        modelAndView.setViewName("employee/profile-details");
        CompetencyRankingProfile profile = profileRepository.getOne(Integer.decode(id));
        String role = "ROLE_MEMBER";
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains("ROLE_MANAGER"))){
            role = "ROLE_MANAGER";
        } else {
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains("ROLE_LEADER"))){
                role = "ROLE_LEADER";
            }
        }
        float seftPoint = 0;
        float reviewPoint = 0;

        modelAndView.addObject("mode", mode);
        modelAndView.addObject("profile", profile);
        modelAndView.addObject("empRole", role);
        modelAndView.addObject("seftPoint", seftPoint);
        modelAndView.addObject("reviewPoint", reviewPoint);
        modelAndView.addObject("pattern", profile.getCompetencyRankingProfileDetails().get(1).getCompetencyRankingPatternDetail().getCompetencyRankingPattern());
        return modelAndView;
    }

    /**
     * Display copy profile Page
     */
    @GetMapping("/profile/copy")
    public ModelAndView showCopyPage(ModelAndView modelAndView
            , @RequestParam("id") String id
            , UserDetails userDetails) {
        CompetencyRankingProfile profile = profileRepository.getOne(Integer.decode(id));
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("employee/profile-copy");

        return modelAndView;
    }
    /**
     * Display delete profile Page
     */
    @GetMapping("/profile/delete")
    public ModelAndView showDeletePage(ModelAndView modelAndView
            , @RequestParam("id") String id
            , UserDetails userDetails) {
        CompetencyRankingProfile profile = profileRepository.getOne(Integer.decode(id));
        modelAndView.addObject("profile", profile);
        modelAndView.setViewName("employee/profile-delete");

        return modelAndView;
    }
    /**
     * Display my profiles page
     */
    @GetMapping("/my-profiles")
    public ModelAndView showMyProfilesPage(ModelAndView modelAndView
            , UserDetails userDetails) {
        modelAndView.setViewName("employee/employee-profile");
        return modelAndView;
    }

    /**
     * Display create profile Page
     */
    @GetMapping("/create")
    public String createNewProfile(ModelAndView modelAndView
            , @RequestParam("period") String period
            , @RequestParam("profileTitle") String profileTitle
            , @RequestParam("template") String template
            , @ModelAttribute("employee") Employee employee
            , UserDetails userDetails) {
        //modelAndView.setViewName("employee/profile-details");
        int index;
//        System.out.println(period);
//        System.out.println(profileTitle);
//        System.out.println(template);
//        System.out.println(employee.getFirstName());

        CompetencyRankingProfile profile;
        CompetencyRankingPattern pattern = patternRepository.findById(Integer.decode(template)).get();
        List<CompetencyRankingProfileDetail> profileDetails;

        profile = new CompetencyRankingProfile();
        profile.setTitle(profileTitle);
        profile.setPeriod(periodRepository.findByPeriodName(period).get());
        profile.setStatusType(statusRepository.findByStatusTypeName("In-Progress").get());
        profile.setEmployee(employee);
        profile.setPattern(pattern);

        profileDetails = new ArrayList<>();
        CompetencyRankingProfileDetail profileDetail;

        List<CompetencyRankingPatternDetail> patternDetails = pattern.getCompetencyRankingPatternDetail();
        for (CompetencyRankingPatternDetail detail : patternDetails
        ) {
            profileDetail = new CompetencyRankingProfileDetail();
            profileDetail.setCompetencyRankingProfile(profile);
            profileDetail.setCompetencyRankingPatternDetail(detail);
            profileDetail.setSource(dataSource.findByDataSourceName("Any where").get());
            profileDetail.setReviewPoint(0);
            profileDetail.setSelfPoint(0);
            profileDetail.setProficientReviewPoinId(detail.getCompetencyComponentDetail().getProficiencyLevels().get(0).getProficiencyLevelId());
            profileDetail.setProficientSeltPointId(detail.getCompetencyComponentDetail().getProficiencyLevels().get(0).getProficiencyLevelId());
            profileDetails.add(profileDetail);

        }
        profile.setCompetencyRankingProfileDetails(profileDetails);

        profile = profileRepository.save(profile);
        index = profile.getRankingProfileId();
        return "redirect:/employee/profile/edit?id=" + index + "&mode=normal";
    }

    /**
     * Display update account Page
     */
    @GetMapping("/update-account")
    public ModelAndView showUpdateAccountPage(ModelAndView modelAndView
            , UserDetails userDetails) {
        UserAccount userAccount = userAccountService.findByEmail(userDetails.getUsername());
        Employee formEmployee = employeeService.getEmployee(userAccount.getEmployee().getEmployeeId());
        modelAndView.addObject("employeeForm", formEmployee);
        modelAndView.addObject("employeeContactForm", employeeService.getEmployeeContact(formEmployee));
        modelAndView.addObject("page", cancelButtonPath(userDetails));
        modelAndView.setViewName("employee/update-account");
        return modelAndView;
    }

    /**
     * Update information of employee
     */
    @PostMapping("/update-account")
    public ModelAndView updateAccount(ModelAndView modelAndView
            , @ModelAttribute("employeeForm") Employee employeeForm
            , @ModelAttribute("employeeContactForm") EmployeeContact employeeContactForm
            , @RequestParam("g-recaptcha-response") String gRecaptchaResponse
            , @SessionAttribute("employee") Employee employee
            , @SessionAttribute("employeeContact") EmployeeContact employeeContact
            , UserDetails userDetails) {
        // validate in order to ensure google recaptcha is valid
        if (reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
            if (employeeService.findEmployeeByEmail(employeeContactForm.getEmail()) == null) {
                // Update information of Employee
                employee.setFirstName(employeeForm.getFirstName());
                employee.setLastName(employeeForm.getLastName());
                employee.setDateOfBirth(employeeForm.getDateOfBirth());
                employee.setJobTitle(employeeForm.getJobTitle());
                employeeService.save(employee);

                // Update information of Employee Contact
                employeeContact.setTelephone(employeeContactForm.getTelephone());
                employeeContact.setAddress1(employeeContactForm.getAddress1());
                employeeContact.setAddress2(employeeContactForm.getAddress2());
                employeeContact.setEmail(employeeContactForm.getEmail());
                employeeService.saveEmployeeContact(employeeContact);

                modelAndView.addObject("successMessage", "Update information is success!");
            } else {
                modelAndView.addObject("errorMessage", "The Email is exist! Please try again!");
            }
        } else {
            modelAndView.addObject("errorMessage", "Invalid reCaptcha!");
        }
        showUpdateAccountPage(modelAndView, userDetails);
        return modelAndView;
    }

    /**
     * Display change password Page
     */
    @GetMapping("/change-password")
    public ModelAndView showChangePasswordPage(ModelAndView modelAndView
            , UserDetails userDetails) {
        modelAndView.addObject("page", cancelButtonPath(userDetails));
        modelAndView.setViewName("employee/change-password");
        return modelAndView;
    }

    /**
     * After we changed the password, the page will be redirected logout page
     * The information will be cleared by Spring Security
     */
    @PostMapping("/change-password")
    public ModelAndView changePassword(ModelAndView modelAndView
                        , @ModelAttribute("userAccount") UserAccount userAccount
                        , @RequestParam("currentPassword") String currentPassword
                        , @RequestParam("newPassword") String newPassword
                        , @RequestParam("g-recaptcha-response") String gRecaptchaResponse
                        , UserDetails userDetails) {
        // validate in order to ensure google recaptcha is valid
        if (reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
            // Validate current-password, ensure the current password have to match the password in database
            if (passwordEncoder.matches(currentPassword,userAccount.getPassword())) {
                // Saving new password with encode password
                userAccount.setPassword(passwordEncoder.encode(newPassword));
                userAccountService.save(userAccount);
                modelAndView.setViewName("redirect:/logout");
                // If we are logged in by 3rd party, The password will be empty
            } else if (null == userAccount.getPassword()) {
                userAccount.setPassword(passwordEncoder.encode(newPassword));
                userAccountService.save(userAccount);
                modelAndView.setViewName("redirect:/logout");
            } else {
                // Return the change-password page and give an error message
                modelAndView.addObject("message", "Current password does not match! Please try again!");
                showChangePasswordPage(modelAndView, userDetails);
            }
        } else {
            // Return the change-password page and give an error message
            modelAndView.addObject("message", "Invalid reCaptCha!");
            showChangePasswordPage(modelAndView, userDetails);
        }
        return modelAndView;
    }

    /**
     * Display change avatar Page
     */
    @GetMapping("/change-avatar")
    public ModelAndView showChangeAvatarPage(ModelAndView modelAndView
            , UserDetails userDetails) {
        modelAndView.addObject("page", cancelButtonPath(userDetails));
        modelAndView.setViewName("employee/change-avatar");
        return modelAndView;
    }

    /**
     * Upload new avatar and store into database
     */
    @PostMapping("/change-avatar")
    public ModelAndView changeAvatar(ModelAndView modelAndView
            , @RequestParam("file") MultipartFile multipartFile
            , @ModelAttribute("employee") Employee employee
            , UserDetails userDetails) throws IOException {
        // Check input file, make sure the input file is image type
        if (multipartFile.getContentType().contains("image") && !multipartFile.isEmpty()) {
            employee.setAvatar(multipartFile.getBytes());
            employeeService.save(employee);
            modelAndView.addObject("successMessage", "Upload avatar is successful!");
        } else {
            modelAndView.addObject("errorMessage", "Please input image type!");
        }
        showChangeAvatarPage(modelAndView, userDetails);
        return modelAndView;
    }

    /**
     * Display Deactivate Account Page
     */
    @GetMapping("/deactivate-account")
    public ModelAndView showDeactivateAccount(ModelAndView modelAndView
            , UserDetails userDetails) {
        modelAndView.addObject("page", cancelButtonPath(userDetails));
        modelAndView.setViewName("employee/deactivate-account");
        return modelAndView;
    }

    /**
     * Setting account status is false to deactivate Account
     */
    @PostMapping("/deactivate-account")
    public ModelAndView doDetactivateAccount(ModelAndView modelAndView
            , @ModelAttribute("userAccount") UserAccount userAccount
            , @RequestParam("g-recaptcha-response") String gRecaptchaResponse
            , UserDetails userDetails) {
        // validate in order to ensure google recaptcha is valid
        if (reCaptchaService.validateReCaptcha(gRecaptchaResponse)) {
            userAccount.setActivated(false);
            userAccountService.save(userAccount);
            // Redirect to Spring Security path: logoutSuccessUrl -> clean UserDetails
            modelAndView.setViewName("redirect:/logout");
        } else {
            modelAndView.addObject("message", "Invalid reCaptcha!");
            showDeactivateAccount(modelAndView, userDetails);
        }
        return modelAndView;
    }

    /**
     * return context Path for a link (button cancel)
     */
    private String cancelButtonPath(UserDetails userDetails) {
        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains("ROLE_MANAGER"))) {
            return "manager";
        }
        return "employee";
    }

}