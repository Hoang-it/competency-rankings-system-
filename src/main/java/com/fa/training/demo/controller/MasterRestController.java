package com.fa.training.demo.controller;

import com.fa.training.demo.dto.EmployeeProfile;
import com.fa.training.demo.dto.PatternDTO;
import com.fa.training.demo.dto.ProfileDetailsDTO;
import com.fa.training.demo.entities.*;

import com.fa.training.demo.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MasterRestController {

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PatternRepository patternRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    PatternDetailRepository patternDetailRepository;

    @Autowired
    ComponentDetailRepository componentDetailRepository;

    @Autowired
    ProfileDetailRepository profileDetailRepository;

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    ProjectKPILevelRepository projectKPILevelRepository;

    @Autowired
    ResponsiveLevelRepository responsiveLevelRepository;

    @Autowired
    RoleProficientRepository roleProficientRepository;

    @Autowired
    PersonalContributeRepository personalContributeRepository;

    @Autowired
    JobTitleRepository jobTitleRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CompentencyResultRepository compentencyResultRepository;

    @Autowired
    JobRankRepository jobRankRepository;

    @RequestMapping("/master/period")
    public List<Period> getAllPeriod(){

        return periodRepository.findAll();
    }

    @RequestMapping("/master/pattern")
    public List<PatternDTO> getAllPattern(@RequestParam("empjbId") int empjbId, @RequestParam("period") String periodName){
        System.out.println(periodName);
        List<CompetencyRankingPattern> patterns = patternRepository.findByJobTitleAndPeriod(jobTitleRepository.findById(empjbId).get(), periodRepository.findByPeriodName(periodName).get());
        List<PatternDTO> patternDTOS = new ArrayList<>();
        PatternDTO pdto;
        for (CompetencyRankingPattern p : patterns
             ) {
            pdto = new PatternDTO();
            pdto.setPatternId(p.getCompetencyRankingPatternId());
            pdto.setPatternName(p.getPatternName());
            patternDTOS.add(pdto);
        }
        return patternDTOS;
    }

    @RequestMapping("/master/profile/pattern-weight")
    public List<PatternWeight> getProfileComponent(@RequestParam("id") String profileId){
        List<PatternWeight> patternWeights = profileRepository.getOne(Integer.decode(profileId))
                .getPattern().getPatternWeights();
//        List<CompetencyComponent> components = new ArrayList<>();
//
//        for (PatternWeight patternWeight : patternWeights
//             ) {
//            System.out.println(patternWeight.getCompetencyComponent().getComponentName());
//            components.add(patternWeight.getCompetencyComponent());
//        }
        return patternWeights;
    }

    @RequestMapping("/master/profile/pattern-details-weight")
    public List<CompetencyRankingPatternDetail> getProfileDetailsRole(@RequestParam("id") String profileId, @RequestParam("cid") String componentId){
        // TO do : chinh sua
        List<CompetencyRankingPatternDetail> patternDetailsWeights = profileRepository.getOne(Integer.decode(profileId))
                .getPattern().getCompetencyRankingPatternDetail();
        List<CompetencyRankingPatternDetail> componentDetailsWeight = new ArrayList<>();
        for (CompetencyRankingPatternDetail patternDetail : patternDetailsWeights
             ) {
            if (patternDetail.getCompetencyComponentDetail().getCompetencyComponent().getComponentId() == Integer.decode(componentId).intValue()){
                componentDetailsWeight.add(patternDetail);
            }
        }
        return  componentDetailsWeight;
    }

    @RequestMapping("/master/profile")
    public List<EmployeeProfile> getAllProfile(@RequestParam("id") String employeeId){
        System.out.println(profileRepository.findAll().size());
        List<CompetencyRankingProfile> profile = profileRepository.findAllByEmployee(employeeRepository.getOne(Integer.decode(employeeId)));
        List<EmployeeProfile> result = new ArrayList<>();
        EmployeeProfile r;
        for (CompetencyRankingProfile p : profile
             ) {
            r = new EmployeeProfile();
            r.setProfileId(p.getRankingProfileId());
            r.setCreated(p.getCreated());
            r.setStatusName(p.getStatusType().getStatusTypeName());
            r.setPreriodName(p.getPeriod().getPeriodName());
            r.setProfileName(p.getTitle());
            if (p.getCompetencyResult() != null){
                r.setRank(p.getCompetencyResult().getJobRank().getJobRankName());
            } else {
                r.setRank("null");
            }

            result.add(r);
        }
        return result;
    }

    @RequestMapping("/master/profile-details")
    public List<ProfileDetailsDTO> getProfileDetails(@RequestParam("pid") String profileId){
        CompetencyRankingProfile profile = profileRepository.getOne(Integer.decode(profileId));
        List<CompetencyRankingProfileDetail> profileDetails = profile.getCompetencyRankingProfileDetails();
        List<ProfileDetailsDTO> profileDetailsDTOS = new ArrayList<>();
        ProfileDetailsDTO profileDetailsDTO;
        for (CompetencyRankingProfileDetail profileDetail : profileDetails
             ) {
            profileDetailsDTO = new ProfileDetailsDTO();
            profileDetailsDTO.setProfileDetailId(profileDetail.getRankingProfileDetailId());
            profileDetailsDTO.setPatternDetailId(profileDetail.getCompetencyRankingPatternDetail().getCompetencyPatternDetailId());
            profileDetailsDTO.setPatternDetailName(profileDetail.getCompetencyRankingPatternDetail().getCompetencyComponentDetail().getComponentDetailName());
            profileDetailsDTO.setReviewPoint(profileDetail.getReviewPoint());
            profileDetailsDTO.setSelfPoint(profileDetail.getSelfPoint());
            profileDetailsDTO.setPatternWeight(profileDetail.getCompetencyRankingPatternDetail().getWeightDetail());
            profileDetailsDTO.setProficientMax(0);
            profileDetailsDTO.setProficientMin(0);
            profileDetailsDTO.setSourceId(profileDetail.getSource().getDataSourceId());
            profileDetailsDTO.setSourceRequired(profileDetail.getCompetencyRankingPatternDetail().getOptional());
            profileDetailsDTO.setProficientSelfPointId(profileDetail.getProficientSeltPointId());
            profileDetailsDTO.setProficientReviewPointId(profileDetail.getProficientReviewPoinId());
            profileDetailsDTO.setPointOfPattern(profileDetail.getCompetencyRankingPatternDetail().getPointOfPatternDetail());
            profileDetailsDTO.setComponentDetailId(profileDetail.getCompetencyRankingPatternDetail().getCompetencyComponentDetail().getComponentDetailId());
            profileDetailsDTOS.add(profileDetailsDTO);
            profileDetailsDTO.setPoint(profileDetail.getSelfPoint());
        }
        return profileDetailsDTOS;
    }

    @RequestMapping("/master/datasource")
    public List<DataSource> getDatasource(){
        return dataSourceRepository.findAll();
    }

    @RequestMapping("/master/role-responsive")
    public List<RoleProficiencyLevel> getRoleResponsive(){
        return roleProficientRepository.findAll();
    }

    @RequestMapping("/master/project-kpi")
    public List<ProjectKPILevel> getProjectKpi(){
        return projectKPILevelRepository.findAll();
    }

    @RequestMapping("/master/responsive")
    public List<ResponsibilityProficiencyLevel> getResponsiveLevel(){
        return responsiveLevelRepository.findAll();
    }

    @RequestMapping("/master/person-contribute")
    public List<PersonalContributionLevel> getPersonContribute(){
        return personalContributeRepository.findAll();
    }

    @RequestMapping("/master/patternDetail")
    public CompetencyRankingPatternDetail getAllPatternDetail(@RequestParam("id") String id){
        return patternDetailRepository.findById(Integer.decode(id)).get();
    }

    @PostMapping("profile/update")
    public void updateProfile(@RequestBody List<ProfileDetailUpdate> profileDetailUpdates
            , @RequestParam("role") String role){
        CompetencyRankingProfileDetail profileDetail;
        DataSource dataSource;
        System.out.println(role);

        for (ProfileDetailUpdate profileDetailUpdate : profileDetailUpdates
             ) {
            System.out.println("1");
            profileDetail = profileDetailRepository.getOne(profileDetailUpdate.getProfileDetailId());
            dataSource = dataSourceRepository.getOne(profileDetailUpdate.getSource());

            if ("ROLE_MEMBER".equals(role)){
                System.out.println("Save seft point");
                profileDetail.setSelfPoint(profileDetailUpdate.getNewPoint());
                profileDetail.setProficientSeltPointId(profileDetailUpdate.getProficientId());
            } else {
                System.out.println("Save review point");
                profileDetail.setReviewPoint(profileDetailUpdate.getNewPoint());
                profileDetail.setProficientReviewPoinId(profileDetailUpdate.getProficientId());

            }

            profileDetail.setSource(dataSource);

            profileDetailRepository.save(profileDetail);
            System.out.println(profileDetailUpdate.getProfileDetailId() + " " +profileDetailUpdate.getNewPoint());
        }
    }

    @PostMapping("profile/submit")
    public void submitProfile(@RequestParam("role") String role
            , @RequestParam("pid") String pid){
        CompetencyRankingProfileDetail profileDetail;
        System.out.println(role);
        CompetencyRankingProfile profile;

        profile = profileRepository.findById(Integer.decode(pid)).get();
        if ("ROLE_MEMBER".equals(role)){
            profile.setStatusType(statusRepository.findByStatusTypeName("Submitted").get());
            profileRepository.save(profile);
            return;
        }
        if ("ROLE_LEADER".equals(role)){
            profile.setStatusType(statusRepository.findByStatusTypeName("Reviewed").get());
            profileRepository.save(profile);
            return;
        }
        if ("ROLE_MANAGER".equals(role)){
            profile.setStatusType(statusRepository.findByStatusTypeName("Approved").get());
            profileRepository.save(profile);
            return;
        }
    }

    @DeleteMapping("profile/delete")
    public void deleteProfile(@RequestParam("pid") String pid){
        compentencyResultRepository.deleteByCompetencyRankingProfile(profileRepository.findById(Integer.decode(pid)).get());
        profileRepository.deleteById(Integer.decode(pid));
    }

    @PostMapping("profile/copy")
    public void copyProfile(@RequestParam("pid") String pid){
        System.out.println("Copy page");
        int index;
//        System.out.println(period);
//        System.out.println(profileTitle);
//        System.out.println(template);
//        System.out.println(employee.getFirstName());
        CompetencyRankingProfile clone;
        CompetencyRankingProfile profile;

        clone = profileRepository.getOne(Integer.decode(pid));
        //CompetencyRankingPattern pattern = patternRepository.findById(Integer.decode(template)).get();
        List<CompetencyRankingProfileDetail> profileDetails;

        profile = new CompetencyRankingProfile();
        profile.setTitle(clone.getTitle() + " copy");
        profile.setPeriod(clone.getPeriod());
        profile.setStatusType(clone.getStatusType());
        profile.setEmployee(clone.getEmployee());
        profile.setPattern(clone.getPattern());

        profileDetails = new ArrayList<>();
        CompetencyRankingProfileDetail profileDetail;

        List<CompetencyRankingProfileDetail> cloneDetails = clone.getCompetencyRankingProfileDetails();
        for (CompetencyRankingProfileDetail detail : cloneDetails
        ) {
            profileDetail = new CompetencyRankingProfileDetail();
            profileDetail.setCompetencyRankingProfile(profile);
            profileDetail.setCompetencyRankingPatternDetail(detail.getCompetencyRankingPatternDetail());
            profileDetail.setSource(detail.getSource());
            profileDetail.setReviewPoint(detail.getReviewPoint());
            profileDetail.setSelfPoint(detail.getSelfPoint());
            profileDetail.setProficientReviewPoinId(detail.getProficientReviewPoinId());
            profileDetail.setProficientSeltPointId(detail.getProficientSeltPointId());
            profileDetails.add(profileDetail);

        }
        profile.setCompetencyRankingProfileDetails(profileDetails);

        profile = profileRepository.save(profile);
    }

    @PostMapping("/new-period")
    public ResponseEntity<Object> createNewPeriod(@RequestBody Period period) {
        Period savedPeriod = periodRepository.save(period);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPeriod.getPeriodId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/new-period/edit/{id}")
    public ResponseEntity<Period> editPeriodView(@PathVariable(value = "id") int periodId) throws NotFoundException {
        Period period = periodRepository.findById(periodId)
                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));

        period.setPeriodName(period.getPeriodName());
        final Period updatedPeriod = periodRepository.save(period);
        return ResponseEntity.ok(updatedPeriod);
    }

//    @PutMapping("/new-period/edit/{id}")
//    public ModelAndView editPeriodAction(HttpServletRequest request,
//                                         @PathVariable int periodId,
//                                         BindingResult bindingResult) throws Exception {
//        Period period = periodRepository.findById(periodId)
//                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("editRequestedPeriod");
//            modelAndView.addObject("period", period);
//
//            return modelAndView;
//        }
//
//        this.periodRepository.save(period);
//
//        modelAndView.setViewName("redirect:/profile-patterns/new-period");
//
//        return modelAndView;
//    }

    @GetMapping("/new-period/copy/{id}")
    public ResponseEntity<Period> copyPeriodView(@PathVariable(value = "id") int periodId,
                                                   @Valid @RequestBody Period periodInfo) throws NotFoundException {
        Period period = periodRepository.findById(periodId)
                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));

        period.setPeriodName(period.getPeriodName());
        final Period updatedPeriod = periodRepository.save(period);
        return ResponseEntity.ok(updatedPeriod);
    }

//    @PostMapping("/new-period/copy/{id}")
//    public ResponseEntity<Period> copyPeriodAction(@PathVariable(value = "id") int periodId,
//                                                 @Valid @RequestBody Period periodInfo) throws NotFoundException {
//        Period period = periodRepository.findById(periodId)
//                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));
//
//        period.setPeriodName(period.getPeriodName());
//        final Period updatedPeriod = periodRepository.save(period);
//        return ResponseEntity.ok(updatedPeriod);
//    }

    @GetMapping("/new-period/delete/{id}")
    public ResponseEntity<Integer> deletePeriodView(@PathVariable(value = "id") int periodId)
            throws NotFoundException {
        Period period = periodRepository.findById(periodId)
                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));

        periodRepository.deleteById(period.getPeriodId());

        return new ResponseEntity<>(periodId, HttpStatus.OK);
    }

//    @DeleteMapping("/new-period/delete/{id}")
//    public ResponseEntity<Integer> deletePeriodAction(@PathVariable(value = "id") int periodId)
//            throws NotFoundException {
//        Period period = periodRepository.findById(periodId)
//                .orElseThrow(() -> new NotFoundException("Period not found on ID:"+ periodId));
//
//        periodRepository.deleteById(period.getPeriodId());
//
//        return new ResponseEntity<>(periodId, HttpStatus.OK);
//    }
}
