package com.fa.training.demo.controller;

import com.fa.training.demo.dto.RankingProfileDTO;
import com.fa.training.demo.entities.*;
import com.fa.training.demo.entities.paging.PagingRequest;
import com.fa.training.demo.repository.CompentencyResultRepository;
import com.fa.training.demo.repository.JobRankRepository;
import com.fa.training.demo.repository.ProfileRepository;
import com.fa.training.demo.repository.StatusRepository;
import com.fa.training.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController{

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private JobRankRepository jobRankRepository;

    @Autowired
    private CompentencyResultRepository compentencyResultRepository;

    @Autowired
    private ProfileDetailService profileDetailService;

    @Autowired
    private PagingRequestService pagingRequestService;

    /**
     * show Manager page after login successfully and the correct role
     */
    @GetMapping("")
    public String showManagerPage() {
        return "manager/manager";
    }

    @GetMapping("/aprrove")
    public ModelAndView approveProfile(@RequestParam("role") String role, @RequestParam("pid") String pid){
        ModelAndView modelAndView = new ModelAndView("manager/manage-profile");
        modelAndView.addObject("profiles", profileService.findAll());
        if ("ROLE_MANAGER".equals(role)){
            CompetencyRankingProfile profile;
            System.out.println("Change Status");
            System.out.println();

            profile = profileRepository.findById(Integer.decode(pid)).get();
            System.out.println(profile.getTitle());
            profile.setStatusType(statusRepository.findByStatusTypeName("Reviewed").get());

            // SET JOB RANK
            CompetencyResult competencyResult = new CompetencyResult();
            competencyResult.setEmployee(profile.getEmployee());
            competencyResult.setJobRank(jobRankRepository.findByJobRankName("Fresher"));
            competencyResult.setCompetencyRankingProfile(profile);
            profile.setCompetencyResult(compentencyResultRepository.save(competencyResult));
            profileRepository.save(profile);
        }
        return modelAndView;
    }
    /**
     * Display manager profile page
     */
    @GetMapping("/manage-profile")
    public ModelAndView showManageProfilePage() {
        return new ModelAndView("manager/manage-profile");
    }

    /**
     * Paging, print and export PDF file on manage profile page with datatable
     */
    @GetMapping("/manage-profile/findAll")
    @ResponseBody
    public ResponseEntity<?> pagingProfile(@RequestParam("start") int start
            , @RequestParam("length") int length
            , @RequestParam("draw") int draw
            , @RequestParam("search[value]") String search
            , @RequestParam("filter_option") String filterStatus) {

        System.out.println(search);
        System.out.println(filterStatus);

        int page = start / length;
        Pageable pageable = PageRequest.of(page, length);

        Page<CompetencyRankingProfile> paging = profileService.getAll4Paging(pageable);

        List<CompetencyRankingProfile> rankingProfiles = paging.getContent();

        //Mapping data to Entity DTO
        List<RankingProfileDTO> listDTO = new ArrayList<>();
        for (CompetencyRankingProfile e: rankingProfiles) {
            RankingProfileDTO rankingProfileDTO = new RankingProfileDTO();
            rankingProfileDTO.setRankingProfileId(e.getRankingProfileId());
            rankingProfileDTO.setFullName(
                    Stream.of(e.getEmployee())
                            .map(a -> String.join(" ", e.getEmployee().getFirstName(), e.getEmployee().getLastName()))
                            .collect(Collectors.joining()));
            rankingProfileDTO.setPeriod(e.getPeriod().getPeriodName());
            rankingProfileDTO.setJobTitle(
                    e.getEmployee().getJobRank() == null ? e.getEmployee().getJobTitle().getJobTitleName()
                    : e.getEmployee().getJobRank().getJobRankName().concat(" ").concat(e.getEmployee().getJobTitle().getJobTitleName()));
            rankingProfileDTO.setSubmittedRank(e.getEmployee().getJobRank() == null ? "" : e.getEmployee().getJobRank().getJobRankName());
            rankingProfileDTO.setStatus(e.getStatusType().getStatusTypeName());
            listDTO.add(rankingProfileDTO);
        }

        PagingRequest request =
                pagingRequestService.request
                        (listDTO, paging.getTotalElements(), paging.getTotalElements(), start, draw);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    /**
     * Display deactivate or activate account page with employee has role MEMBER
     */
    @GetMapping("/manage-employee")
    public ModelAndView showDeactivateEmployee(ModelAndView modelAndView) {
        List<UserAccount> listUserAccount = userAccountService.findAllEmployee();
        List<UserAccount> userAccounts = userAccountService.findAllEmployee(0, 3);
        modelAndView.addObject("list", userAccounts);
        modelAndView.addObject("listUserAccount", listUserAccount);
        modelAndView.setViewName("manager/manage-employee-account");
        return modelAndView;
    }

    /**
     * Using ajax paging the manage-employee page when we click on button 'Load more...'
     */
    @GetMapping("/paging")
    @ResponseBody
    public ResponseEntity<List<UserAccount>> pagingUserAccount(@RequestParam("page") Integer page
                                                , @RequestParam("size") Integer size) {
        return new ResponseEntity<>(userAccountService.findAllEmployee(page, size), HttpStatus.OK);
    }

    /**
     * Using Ajax to activate employee's account
     */
    @PutMapping("/activate-employee")
    @ResponseBody
    public void activateEmployee(@RequestParam("id") Integer id) {
        UserAccount userAccount = userAccountService.findById(id);
        userAccount.setActivated(true);
        userAccountService.save(userAccount);
    }

    /**
     * Using Ajax to deactivate employee's account
     */
    @PutMapping("/deactivate-employee")
    @ResponseBody
    public void deactivateEmployee(@RequestParam("id") Integer id) {
        UserAccount userAccount = userAccountService.findById(id);
        userAccount.setActivated(false);
        userAccountService.save(userAccount);
    }

}
