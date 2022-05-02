package com.fa.training.demo.controller;

import com.fa.training.demo.dto.RankingPatternDTO;
import com.fa.training.demo.entities.CompetencyRankingPattern;
import com.fa.training.demo.entities.CompetencyRankingPatternDetail;
import com.fa.training.demo.entities.PatternWeight;
import com.fa.training.demo.entities.paging.PagingRequest;
import com.fa.training.demo.enumeric.StatusEnum;
import com.fa.training.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/profile-patterns")
public class CopyPatternController extends BaseController{

    @Autowired
    private PeriodService periodService;

    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private PatternDetailService patternDetailService;

    @Autowired
    private PatternWeightService patternWeightService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PagingRequestService pagingRequestService;


    /**
     * show Profile-Patterns Page
     */
    @GetMapping("/profile-pattern")
    public ModelAndView showProfilePattern(ModelAndView modelAndView) {
        modelAndView.setViewName("profile-patterns/profile-patterns");
        return modelAndView;
    }

    /**
     * Using datatable to paging
     */
    @GetMapping("profile-pattern/findAll")
    @ResponseBody
    public ResponseEntity<?> pagingPattern(@RequestParam("start") int start
            , @RequestParam("search[value]") String search
            , @RequestParam("length") int length
            , @RequestParam("draw") int draw
            , @RequestParam("filter_option") String condition) {

        // Sort by...
        Sort sortName = Sort.by("patternName");
        Sort sortPeriod = Sort.by("period");
        Sort sortCreated = Sort.by("created");
        Sort sortStatus = Sort.by("statusType");
        Sort groupBySort = sortName.and(sortPeriod).and(sortCreated).and(sortStatus);

        int page = start / length;
        Pageable pageable = PageRequest.of(page, length, groupBySort);

        Page<CompetencyRankingPattern> pageData;

        if (!condition.isEmpty()) {
            pageData = patternService.findAllBySearchAndCondition(condition, search, pageable);
        } else {
            pageData = !search.isEmpty() ?
                    patternService.findAllBySearch(search, pageable) : patternService.findAll(pageable);
        }
        List<CompetencyRankingPattern> rankingPatterns = pageData.getContent();

        // Mapping data to EntityDTO
        List<RankingPatternDTO> list = new ArrayList<>();
        for (CompetencyRankingPattern e: rankingPatterns) {
            RankingPatternDTO rankingPatternDTO = new RankingPatternDTO();
            rankingPatternDTO.setPatternId(e.getCompetencyRankingPatternId());
            rankingPatternDTO.setPatternName(e.getPatternName());
            rankingPatternDTO.setPeriod(e.getPeriod().getPeriodName());
            rankingPatternDTO.setCreated(e.getCreated());
            rankingPatternDTO.setStatus(e.getStatusType().getStatusTypeName());
            list.add(rankingPatternDTO);
        }

        // Setting request of page
        PagingRequest request =
                pagingRequestService.request
                        (list, pageData.getTotalElements(), pageData.getTotalElements(), start, draw);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    /**
     * Using ajax to delete Pattern
     */
    @DeleteMapping("/delete-pattern")
    @ResponseBody
    public void deletePattern(@RequestParam("patternId") Integer id) {
        patternService.deletePatternById(id);
    }

    /**
     * display copy-pattern page to copy new Pattern
     */
    @GetMapping("/copy-pattern")
    public ModelAndView copyPatternPage(@RequestParam("patternId") Integer id) {
        ModelAndView modelAndView = new ModelAndView("profile-patterns/copy-profile-pattern");
        modelAndView.addObject("pattern", patternService.findById(id));
        modelAndView.addObject("periods", periodService.findAll());
        modelAndView.addObject("listPattern", jobTitleService.findAll());
        return modelAndView;
    }

    /**
     * copy old pattern detail to new pattern detail
     */
    @PostMapping("/copy-pattern")
    public ModelAndView doCopyPattern(@RequestParam("patternId") Integer patternId,
                                      @RequestParam("period") Integer periodId,
                                      @RequestParam("jobTitle") Integer jobTitleId) {

        CompetencyRankingPattern pattern = patternService.findById(patternId);

        CompetencyRankingPattern newPattern = new CompetencyRankingPattern();
        newPattern.setPeriod(periodService.findById(periodId));
        newPattern.setJobTitle(jobTitleService.findById(jobTitleId));
        newPattern.setPatternName(jobTitleService.findById(jobTitleId).getJobTitleName());
        newPattern.setStatusType(statusService.getStatus(StatusEnum.IN_PROGRESS.getKey()));
        // make sure entity exist in database (flushed entity)
        patternService.save(newPattern);
        // Get old list pattern detail
        List<CompetencyRankingPatternDetail> patternDetails = pattern.getCompetencyRankingPatternDetail();
        List<CompetencyRankingPatternDetail> newPatternDetails = new ArrayList<>();
        // add old list pattern detail to new pattern detail
        for (CompetencyRankingPatternDetail e : patternDetails) {
            CompetencyRankingPatternDetail patternDetail = new CompetencyRankingPatternDetail();
            patternDetail.setCompetencyRankingPattern(newPattern);
            patternDetail.setWeightDetail(e.getWeightDetail());
            patternDetail.setPointOfPatternDetail(e.getPointOfPatternDetail());
            patternDetail.setOptional(e.getOptional());
            patternDetail.setMaxLevel(e.getMaxLevel());
            patternDetail.setCompetencyComponentDetail(e.getCompetencyComponentDetail());
            patternDetailService.save(patternDetail);
            newPatternDetails.add(e);
        }
        // set list new pattern detail to new pattern
        newPattern.setCompetencyRankingPatternDetail(newPatternDetails);

        // Get old list pattern weight
        List<PatternWeight> patternWeights = pattern.getPatternWeights();
        List<PatternWeight> newPatternWeight = new ArrayList<>();

        // Add old list pattern weight to new pattern weight
        for (PatternWeight e : patternWeights) {
            PatternWeight patternWeight = new PatternWeight();
            patternWeight.setCompetencyComponent(e.getCompetencyComponent());
            patternWeight.setCompetencyRankingPattern(newPattern);
            patternWeight.setWeight(e.getWeight());
            patternWeight.setBasePoint(e.getBasePoint());
            patternWeightService.save(patternWeight);
            newPatternWeight.add(e);
        }
        // set list new pattern weight to new pattern
        newPattern.setPatternWeights(newPatternWeight);

        // Copy old pattern to new pattern completely & save it.
        patternService.save(newPattern);
        ModelAndView modelAndView = new ModelAndView("redirect:/profile-patterns/new-pattern-detail?location=0&patternId=" + newPattern.getCompetencyRankingPatternId());

        return modelAndView;
    }

}
