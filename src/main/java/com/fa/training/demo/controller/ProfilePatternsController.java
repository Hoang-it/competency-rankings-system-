package com.fa.training.demo.controller;

import com.fa.training.demo.dto.PeriodDTO;
import com.fa.training.demo.dto.RankingPatternDTO;
import com.fa.training.demo.entities.*;
import com.fa.training.demo.entities.paging.PagingRequest;
import com.fa.training.demo.enumeric.StatusEnum;
import com.fa.training.demo.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"patternDetail", "pattern"})
@RequestMapping("/profile-patterns")
public class ProfilePatternsController extends BaseController {

    @Autowired
    private PeriodService periodService;

    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private PatternDetailService patternDetailService;

    @Autowired
    private ComponentDetailService componentDetailService;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private PatternWeightService patternWeightService;

    @Autowired
    private ProficiencyLevelService proficiencyLevelService;

    @Autowired
    private StatusService statusService;

    @GetMapping("/new-pattern")
    public ModelAndView goToCreateNewPattern() {
        List<Period> periods = periodService.findAll();
        List<JobTitle> jobTitles = jobTitleService.findAll();
        ModelAndView mv = new ModelAndView("manager/create-profile-pattern");
        mv.addObject("periods", periods);
        mv.addObject("jobTitles", jobTitles);
        return mv;
    }

    private List<String> listComponent = Arrays.asList(new String[]{"new-ranking-responsibility", "new-business-contributions", "new-professional-skills", "new-engineering-skills", "new-non-engineering-skills", "new-natural-language-skills", "new-knowledge-degrees", "new-pattern-summary"});

    @PostMapping("/new-pattern")
    public ModelAndView createNewPattern(@RequestParam(value = "location") int location,
                                         @RequestParam("periodId") String periodId,
                                         @RequestParam("jobTitleId") String jobTitleId) {
        Period period = periodService.findById(Integer.valueOf(periodId));
        JobTitle jobTitle = jobTitleService.findById(Integer.valueOf(jobTitleId));

        CompetencyRankingPattern pattern = new CompetencyRankingPattern();
        pattern.setPeriod(period);
        pattern.setJobTitle(jobTitle);
        pattern.setPatternName(jobTitle.getJobTitleName());
        pattern.setStatusType(statusService.getStatus(StatusEnum.IN_PROGRESS.getKey()));
        patternService.save(pattern);

        List<PatternWeight> patternWeights = new ArrayList<>();

        PatternWeight patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(1));
        patternWeight.setWeight(20);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(2));
        patternWeight.setWeight(15);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(3));
        patternWeight.setWeight(18);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(4));
        patternWeight.setWeight(10);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(5));
        patternWeight.setWeight(10);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(6));
        patternWeight.setWeight(18);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        patternWeight = new PatternWeight();
        patternWeight.setCompetencyRankingPattern(pattern);
        patternWeight.setCompetencyComponent(componentService.findById(7));
        patternWeight.setWeight(9);
        patternWeightService.save(patternWeight);
        patternWeights.add(patternWeight);

        pattern.setPatternWeights(patternWeights);
        patternService.save(pattern);

        List<CompetencyRankingPatternDetail> competencyRankingPatternDetails = new ArrayList<>();
        CompetencyRankingPatternDetail patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(1));
        patternDetail.setWeightDetail(15D);
        patternDetail.setPointOfPatternDetail(15D);
        patternDetail.setMaxLevel("Initiate");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(2));
        patternDetail.setWeightDetail(5D);
        patternDetail.setPointOfPatternDetail(5D);
        patternDetail.setMaxLevel("Very High");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);


        competencyRankingPatternDetails = new ArrayList<>();
        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(3));
        patternDetail.setWeightDetail(4D);
        patternDetail.setPointOfPatternDetail(4D);
        patternDetail.setMaxLevel(">21 KPI/REC Points");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(4));
        patternDetail.setWeightDetail(10D);
        patternDetail.setPointOfPatternDetail(10D);
        patternDetail.setMaxLevel(">30 man-month");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(5));
        patternDetail.setWeightDetail(1D);
        patternDetail.setPointOfPatternDetail(1D);
        patternDetail.setMaxLevel(">21 Points");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(6));
        patternDetail.setWeightDetail(6D);
        patternDetail.setPointOfPatternDetail(6D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(7));
        patternDetail.setWeightDetail(6D);
        patternDetail.setPointOfPatternDetail(6D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(8));
        patternDetail.setWeightDetail(6D);
        patternDetail.setPointOfPatternDetail(6D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);


        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(38));
        patternDetail.setWeightDetail(4D);
        patternDetail.setPointOfPatternDetail(4D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(34));
//        patternDetail.setWeightDetail(10);
//        patternDetail.setMaxLevel("Master");
//        patternDetail.setOptional("required");
//        patternDetail.setCompetencyRankingPattern(pattern);
//        competencyRankingPatternDetails.add(patternDetail);
//        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(16));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(17));
        patternDetail.setWeightDetail(4D);
        patternDetail.setPointOfPatternDetail(4D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(18));
        patternDetail.setWeightDetail(4D);
        patternDetail.setPointOfPatternDetail(4D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);


        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(39));
        patternDetail.setWeightDetail(4D);
        patternDetail.setPointOfPatternDetail(4D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(34));
//        patternDetail.setWeightDetail(10);
//        patternDetail.setMaxLevel("Master");
//        patternDetail.setOptional("required");
//        patternDetail.setCompetencyRankingPattern(pattern);
//        competencyRankingPatternDetails.add(patternDetail);
//        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(21));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(22));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(23));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(24));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(25));
        patternDetail.setWeightDetail(2D);
        patternDetail.setPointOfPatternDetail(2D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(26));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(27));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(28));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(29));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(30));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(31));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Native");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);


        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(32));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Master");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(33));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Post Master's Degree");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);

        patternDetail = new CompetencyRankingPatternDetail();
        patternDetail.setCompetencyComponentDetail(componentDetailService.findById(34));
        patternDetail.setWeightDetail(3D);
        patternDetail.setPointOfPatternDetail(3D);
        patternDetail.setMaxLevel("Engineering Level 4");
        patternDetail.setOptional("required");
        patternDetail.setCompetencyRankingPattern(pattern);
        competencyRankingPatternDetails.add(patternDetail);
        patternDetailService.save(patternDetail);


        pattern.setCompetencyRankingPatternDetail(competencyRankingPatternDetails);
        patternService.save(pattern);


        return new ModelAndView("redirect:/profile-patterns/new-pattern-detail?location=" + location + "&patternId=" + pattern.getCompetencyRankingPatternId());
    }

    @GetMapping("/new-pattern-detail")
    public ModelAndView createComponentDetailOfPattern(
            @RequestParam("patternId") int patternId,
            @RequestParam(value = "location") int location) {
        if (location == 7) {
            List<CompetencyComponent> components = componentService.findAll();
            List<PatternWeight> patternWeights = patternWeightService.findByPatternId(patternId);
            for (PatternWeight e : patternWeights) {
                List<CompetencyComponentDetail> componentDetails = e.getCompetencyComponent().getCompetencyComponentDetail();
                Double point = 0D;
                for (CompetencyComponentDetail c : componentDetails) {
                    if (patternDetailService.findByComponentDetailIdAndPatternId(c.getComponentDetailId(), patternId) != null) {
                        CompetencyRankingPatternDetail patternDetail = patternDetailService.findByComponentDetailIdAndPatternId(c.getComponentDetailId(), patternId);
                        if(!patternDetail.getCompetencyComponentDetail().getComponentDetailName().equals("test")) {
                            point = point + patternDetail.getPointOfPatternDetail();
                        }
                    }
                }
                e.setBasePoint(point);
                patternWeightService.save(e);
            }
            ModelAndView mv = new ModelAndView("manager/" + listComponent.get(location));
            mv.addObject("patternWeights", patternWeights);
            mv.addObject("patternId", patternId);
            return mv;

        } else {
            List<CompetencyComponentDetail> competencyComponentDetails = componentDetailService.findComponentDetailByComponent(componentService.findById(location + 1));
            List<CompetencyRankingPatternDetail> patternDetails = new ArrayList<>();
            PatternDetailListWrapper patternDetailListWrapper = new PatternDetailListWrapper();
            for (CompetencyComponentDetail e : competencyComponentDetails) {
                System.out.println(e.getComponentDetailName() + " " + e.getComponentDetailId());
                if (patternDetailService.findByComponentDetailIdAndPatternId(e.getComponentDetailId(), patternId) != null) {
                    CompetencyRankingPatternDetail patternDetail = patternDetailService.findByComponentDetailIdAndPatternId(e.getComponentDetailId(), patternId);
                    patternDetails.add(patternDetail);

                }
                patternDetailListWrapper = new PatternDetailListWrapper();
                patternDetailListWrapper.setPatternDetails(patternDetails);

            }
            ModelAndView mv = new ModelAndView("manager/" + listComponent.get(location));
            mv.addObject("location", location);
            mv.addObject("patternId", patternId);
            mv.addObject("patternComponentListWrapper", patternDetailListWrapper);
            return mv;
        }
    }

    @PostMapping("/create-pattern-detail")
    public ModelAndView createResponsibilities(
            @RequestParam(value = "newComponentDetail", required = false) String componentDetailId,
            @RequestParam(value = "saveAndFinish",required = false) String finish,
            @RequestParam(value = "addNewComponentDetail", required = false) String check,
            @RequestParam("location") int location,
            @RequestParam("patternId") int patternId,
            @ModelAttribute("patternComponentListWrapper") PatternDetailListWrapper patternDetailListWrapper,
            @RequestParam(value = "delete", required = false) Integer deleteButton) {
        if(null!=finish){
            return new ModelAndView("/profile-patterns/profile-patterns");
        }
        if (null != deleteButton) {
            CompetencyRankingPatternDetail patternDetail = patternDetailService.findById(Integer.valueOf(deleteButton));
            PatternWeight patternWeight = patternWeightService.findByPatternIdAndComponentId(patternId, location + 1);
            System.out.println(patternWeight.getWeight());
            System.out.println(patternDetailListWrapper.getPatternDetails().size());
            for (CompetencyRankingPatternDetail e : patternDetailListWrapper.getPatternDetails()) {
                if (location == 2 || location == 3) {
                    e.setWeightDetail(Double.valueOf(patternWeight.getWeight()) / (patternDetailListWrapper.getPatternDetails().size() - 2));
                } else {
                    e.setWeightDetail(Double.valueOf(patternWeight.getWeight()) / (patternDetailListWrapper.getPatternDetails().size() - 1));
                }
                patternDetailService.save(e);
            }
            patternDetailService.delete(patternDetail);
        } else if ("Add New Component Detail".equals(check)) {
            CompetencyComponentDetail componentDetail = componentDetailService.findById(Integer.valueOf(componentDetailId));
            PatternWeight patternWeight = patternWeightService.findByPatternIdAndComponentId(patternId, location + 1);
            for (CompetencyRankingPatternDetail e : patternDetailListWrapper.getPatternDetails()) {
                CompetencyRankingPatternDetail patternDetail = new CompetencyRankingPatternDetail();
                if (e.getCompetencyComponentDetail().getComponentDetailName().equals("test")) {
                    if (patternDetailService.findByComponentDetailIdAndPatternId(componentDetail.getComponentDetailId(), patternId) != null) {
                        patternDetail = patternDetailService.findByComponentDetailIdAndPatternId(componentDetail.getComponentDetailId(), patternId);
                    }
                    patternDetail.setCompetencyComponentDetail(componentDetail);
                    patternDetail.setOptional(e.getOptional());
                    patternDetail.setWeightDetail((Double.valueOf(patternWeight.getWeight()) / patternDetailListWrapper.getPatternDetails().size()));
                    patternDetail.setMaxLevel(e.getMaxLevel());
                    patternDetail.setCompetencyRankingPattern(patternService.findById(patternId));
                    patternDetailService.save(patternDetail);
                } else {
                    e.setWeightDetail(Double.valueOf(patternWeight.getWeight()) / patternDetailListWrapper.getPatternDetails().size());
                    patternDetailService.save(e);
                }
            }
        } else {
            for (CompetencyRankingPatternDetail e : patternDetailListWrapper.getPatternDetails()) {
                ProficiencyLevel level = proficiencyLevelService.findByProficiencyLevelNameAndComponentDetailId(e.getMaxLevel(),e.getCompetencyComponentDetail().getComponentDetailId()).get(0);
                e.setPointOfPatternDetail(e.getWeightDetail()/e.getCompetencyComponentDetail().getProficiencyLevels().size()*(level.getProficiencyLevel()+1));
                patternDetailService.save(e);

            }
            location += 1;
        }
        return new ModelAndView("redirect:/profile-patterns/new-pattern-detail?location=" + location + "&patternId=" + patternId);
    }

    @PostMapping("/changeStatus")
    public ModelAndView changeStatus(@RequestParam("patternId") String patternId){
        System.out.println(patternId);
        CompetencyRankingPattern pattern = patternService.findById(Integer.valueOf(patternId));
        pattern.setStatusType(statusService.getStatus(StatusEnum.APPROVED.getKey()));
        patternService.save(pattern);

        System.out.println(pattern.getCompetencyRankingPatternDetail().size());
        List<CompetencyRankingPatternDetail> patternDetails = new ArrayList<>();
        patternDetails.addAll(pattern.getCompetencyRankingPatternDetail());
        for(CompetencyRankingPatternDetail e : patternDetails){
            if(e.getCompetencyComponentDetail().getComponentDetailId()== 38 || e.getCompetencyComponentDetail().getComponentDetailId()==39){
                pattern.getCompetencyRankingPatternDetail().remove(e);
                patternService.save(pattern);
            }
        }
        CompetencyRankingPatternDetail competencyRankingPatternDetail = patternDetailService.findByComponentDetailIdAndPatternId(38,Integer.valueOf(patternId));
        patternDetailService.delete(competencyRankingPatternDetail);
        competencyRankingPatternDetail = patternDetailService.findByComponentDetailIdAndPatternId(39,Integer.valueOf(patternId));
        patternDetailService.delete(competencyRankingPatternDetail);
        return new ModelAndView("/profile-patterns/profile-patterns");

    }


    @GetMapping("/new-period")
    public ModelAndView goToCreatePeriod() {
        ModelAndView mv = new ModelAndView("manager/create-profile-period");
//        mv.addObject("periods", periodService.findAll());
        return mv;
    }

    @PostMapping("/newPeriodCreation")
    public ModelAndView newPeriodCreation(
            @RequestParam(value = "newPeriodName") String periodName
    ) {
        Period brandNewPeriod = new Period();
        if (periodName != null) {
            brandNewPeriod.setPeriodName(periodName);
            periodService.save(brandNewPeriod);
            return new ModelAndView("redirect:/profile-patterns/new-period");
        } else {
            return new ModelAndView();
        }
    }

    @GetMapping("/new-period/edit/{id}")
    public ModelAndView editPeriodView(@PathVariable int id) throws NotFoundException {
        Period period = this.periodService.findById(id);

        if (period == null) {
            throw new NotFoundException("Cannot find requested period with ID: " + id);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("editRequestedPeriod");
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @PutMapping("/new-period/edit/{id}")
    public ModelAndView editPeriodAction(HttpServletRequest request,
                                         @PathVariable int id,
                                         BindingResult bindingResult) throws Exception {
        Period period = this.periodService.findById(id);

        if (period == null) {
            throw new NotFoundException("Cannot find requested period with ID: " + id);
        }

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("editRequestedPeriod");
            modelAndView.addObject("period", period);

            return modelAndView;
        }

        this.periodService.save(period);

        modelAndView.setViewName("redirect:/profile-patterns/new-period");

        return modelAndView;
    }

    @PostMapping("/new-period/copy/{id}")
    public ModelAndView copyPeriodView(@PathVariable int id) throws NotFoundException {
        Period period = periodService.findById(id);
        if (period == null) {
            throw new NotFoundException("Cannot find requested period with ID: " + id);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("copyRequestedPeriod");
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @DeleteMapping("/new-period/delete/{id}")
    public ResponseEntity<Integer> deletePeriod(@PathVariable Integer id) {
        boolean checkRemoval = periodService.deletePeriod(id);
        if (checkRemoval == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/new-period/findAllPeriods")
    @ResponseBody
    public ResponseEntity<?> getAll(@RequestParam("start") int start
            , @RequestParam("search[value]") String search
            , @RequestParam("length") int length
            , @RequestParam("draw") int draw) throws Exception {
        System.out.println("start: " + start + " length: " + length + " draw: " + draw);
        System.out.println("search " + search);

        Sort sortPeriod = Sort.by("periodName");
        Sort sortCreatedDate = Sort.by("created");
        Sort groupBySort = sortPeriod.and(sortCreatedDate);

        int page = start / length;
        Pageable paging = PageRequest.of(page, length, groupBySort);

        Page<Period> pageDataByPeriodName =
                !search.isEmpty() ? periodService.getAllPeriods(paging, search) : periodService.findAllPeriods(paging);

        List<Period> periodContents = pageDataByPeriodName.getContent();

        List<PeriodDTO> list = new ArrayList<>();
        for (Period p: periodContents) {
            PeriodDTO periodDTO = new PeriodDTO();
            periodDTO.setPeriodId(p.getPeriodId());
            periodDTO.setPeriodName(p.getPeriodName());
            periodDTO.setCreated(p.getCreated());
            list.add(periodDTO);
        }

        // Setting request for page
        PagingRequest<PeriodDTO> request = new PagingRequest<>();
        request.setData(list);
        request.setRecordsTotal(pageDataByPeriodName.getTotalElements());
        request.setRecordsFiltered(pageDataByPeriodName.getTotalElements());
        request.setStart(start);
        request.setDraw(draw);
        System.out.println(pageDataByPeriodName.getTotalPages());

        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
