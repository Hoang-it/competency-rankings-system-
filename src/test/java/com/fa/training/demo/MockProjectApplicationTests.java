package com.fa.training.demo;

import com.fa.training.demo.entities.*;
import com.fa.training.demo.repository.*;
import com.fa.training.demo.repository.ProjectKPILevelRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MockProjectApplicationTests {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PatternRepository patternRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PatternWeightRepository patternWeightRepository;

    @Autowired
    PatternDetailRepository patternDetailRepository;

    @Autowired
    ComponentDetailRepository componentDetailRepository;

    @Autowired
    ProjectKPILevelRepository projectKPILevelRepository;

    @Autowired
    ResponsiveLevelRepository responsiveLevelRepository;

    @Autowired
    RoleProficientRepository roleProficientRepository;

    @Autowired
    PersonalContributeRepository personalContributeRepository;

    @Autowired
    DataSourceRepository dataSourceRepository;

    @Autowired
    ProficientcyLevelRepository proficientcyLevelRepository;
    @Test
    void contextLoads() {
        System.out.println("Testing!!");
    }

    @Test
    @Order(1)
    void savePeriod(){
        List<Period> periods = new ArrayList<>();
        Period period = null;
        for (int i = 0; i < 3; i++) {
            period = new Period();
            period.setPeriodName("S" + i + "-" + LocalDateTime.now().getYear());
            periods.add(period);
        }
        periodRepository.saveAll(periods);
    }

    @Test
    @Order(2)
    void saveStatus(){
        List<StatusType> statusTypes = new ArrayList<>();
        String[] types = {"In-Process", "Pending", "Reviewed", "Approved", "Closed"};
        StatusType statusType = null;
        for (int i = 0; i < 5; i++) {
            statusType = new StatusType();
            statusType.setStatusTypeName(types[i]);
            statusTypes.add(statusType);
        }
        statusRepository.saveAll(statusTypes);
    }

    @Test
    @Order(3)
    void saveEmployee(){
        List<Employee> employees = new ArrayList<>();

        Employee employee = null;
        for (int i = 0; i < 5; i++) {
            employee = new Employee();
            employee.setCode("EMP0" + i);
            employees.add(employee);
        }
        employeeRepository.saveAll(employees);
    }
    @Test
    @Order(4)
    void saveComponent() {
        Set<CompetencyComponent> components = new HashSet<>();
        String[] componentList = {"Roles and Responsibilities"
                , "Professional Skill"
                , "Knowledge and Qualification"
                , "Engineering skills"
                , "Non-engineering skills"
                , "Projec Management Skill"
                , "Bussiness Contribution"};
        String[] idNameList = {"role"
                , "professional"
                , "knowledge"
                , "engineer"
                , "nonEngineer"
                , "management"
                , "bussiness"};
        CompetencyComponent component = null;
        for (int i = 0; i < 7; i++) {
            component = new CompetencyComponent();
            component.setComponentName(componentList[i]);
            component.setIdName(idNameList[i]);
            List<CompetencyComponentDetail> competencyComponentDetails = new ArrayList<>();
            CompetencyComponentDetail detail;
            switch (i){
                case 0:
                    System.out.println("I : 0");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Responsidbilities and Capacities");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Projec Management Role");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    System.out.println(competencyComponentDetails.size());
                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 1:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Staff Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Risk Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Project General Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 2:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Methologies and Knowlegde");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Education and Qualification");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Profession Certification");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 3:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Project Analysis Activities");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Project Management Reporting");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Software Engineering Skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Cloud Computing Skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Specialized Software and tools");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 4:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Bussiness or Domain Skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("EHP function and Application Skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Product and Service Management skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("PreSale Technical sales skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Workplace skill and soft skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("foreign language skill");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 5:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Develop Initial Project Plan");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Scope Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Tasks Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Cost Control and Budget Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Quality Management");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;
                case 6:
                    System.out.println("I : 1");

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Organization Contrilbute");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Project Contribution");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Training and Coaching Contribution");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    detail = new CompetencyComponentDetail();
                    detail.setComponentDetailName("Invidual or Person Contribute");
                    detail.setCompetencyComponent(component);
                    competencyComponentDetails.add(detail);

                    component.setCompetencyComponentDetail(competencyComponentDetails);
                    componentRepository.save(component);
                    break;

            }
            components.add(component);
        }

    }

//    @Test
//    @Transactional
//    @Modifying
//    void updatePeriod(){
//        Period periodToUpdate = periodRepository.getOne(1);
//
//        periodToUpdate.setPeriodName("S1-2021");
//        periodRepository.saveAndFlush(periodToUpdate);
//        System.out.println(periodRepository.getOne(1).getPeriodName());
//    }

//    @Test
//    @Order(5)
//    void savePattern() {
//        CompetencyRankingPattern pattern;
//
//        //pattern = patternRepository.findByPatternName("Software Engineer Java").get();
//        pattern = new CompetencyRankingPattern();
//        pattern.setPatternName("Software Engineer Java");
//
//        List<CompetencyRankingPatternDetail> patternDetails;
//        CompetencyRankingPatternDetail patternDetail;
//
//        patternDetails = new ArrayList<>();
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Responsidbilities and Capacities").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Projec Management Role").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Staff Management").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Risk Management").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project General Management").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Methologies and Knowlegde").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Education and Qualification").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Profession Certification").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Organization Contrilbute").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project Contribution").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Training and Coaching Contribution").get());
//        patternDetails.add(patternDetail);
//
//        pattern.setCompetencyRankingPatternDetail(patternDetails);
//        patternRepository.saveAndFlush(pattern);
//
//        //pattern = patternRepository.findByPatternName("Project Manager Product").get();
//        pattern = new CompetencyRankingPattern();
//        pattern.setPatternName("Project Manager Product");
//
//        patternDetails = new ArrayList<>();
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Responsidbilities and Capacities").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Projec Management Role").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Staff Management").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project General Management").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Methologies and Knowlegde").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Education and Qualification").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Profession Certification").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Organization Contrilbute").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project Contribution").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Training and Coaching Contribution").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Software Engineering Skill").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project Analysis Activities").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Project Management Reporting").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("Bussiness or Domain Skill").get());
//        patternDetails.add(patternDetail);
//
//        patternDetail = new CompetencyRankingPatternDetail();
//        patternDetail.setCompetencyRankingPattern(pattern);
//        patternDetail.setOrderNumber(1);
//        patternDetail.setWeightDetail(15);
//        patternDetail.setCompetencyComponentDetail(componentDetailRepository.findByComponentDetailName("EHP function and Application Skill").get());
//        patternDetails.add(patternDetail);
//
//        pattern.setCompetencyRankingPatternDetail(patternDetails);
//        patternRepository.saveAndFlush(pattern);
//    }


//    @Test
//    @Order(6)
//    void savePatternWeight() {
//        PatternWeight patternWeight;
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Software Engineer Java").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Roles and Responsibilities").get());
//        patternWeight.setOrder(1);
//        patternWeight.setWeight(30);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Software Engineer Java").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Professional Skill").get());
//        patternWeight.setOrder(2);
//        patternWeight.setWeight(20);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Software Engineer Java").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Knowledge and Qualification").get());
//        patternWeight.setOrder(3);
//        patternWeight.setWeight(20);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Software Engineer Java").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Bussiness Contribution").get());
//        patternWeight.setOrder(4);
//        patternWeight.setWeight(30);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Project Manager Product").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Roles and Responsibilities").get());
//        patternWeight.setOrder(1);
//        patternWeight.setWeight(30);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Project Manager Product").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Professional Skill").get());
//        patternWeight.setOrder(2);
//        patternWeight.setWeight(20);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Project Manager Product").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Knowledge and Qualification").get());
//        patternWeight.setOrder(3);
//        patternWeight.setWeight(20);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Project Manager Product").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Engineering skills").get());
//        patternWeight.setOrder(4);
//        patternWeight.setWeight(20);
//        patternWeightRepository.save(patternWeight);
//
//        patternWeight = new PatternWeight();
//        patternWeight.setCompetencyRankingPattern(patternRepository.findByPatternName("Project Manager Product").get());
//        patternWeight.setCompetencyComponent(componentRepository.findByComponentName("Non-engineering skills").get());
//        patternWeight.setOrder(5);
//        patternWeight.setWeight(10);
//        patternWeightRepository.save(patternWeight);
////
////        patternWeight = new PatternWeight();
////        pattern.setPatternName("Project Manager Product");
////        patternWeightRepository.save(patternWeight);
//    }


    @Test
    @Order(7)
    void saveDataSource(){
        List<DataSource> dataSources = new ArrayList<>();

        DataSource dataSource;

        dataSource = new DataSource();
        dataSource.setDataSourceName("Any where");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("Owner");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("Jira");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("Aka link");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("CTC");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("PMC");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("POD");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("OB");
        dataSources.add(dataSource);

        dataSource = new DataSource();
        dataSource.setDataSourceName("BOD");
        dataSources.add(dataSource);

        dataSourceRepository.saveAll(dataSources);
    }

    @Test
    @Order(8)
    void savePersonalContribute(){
        List<PersonalContributionLevel> personalContributionLevels = new ArrayList<>();

        PersonalContributionLevel personalContributionLevel;

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("No Responsivebility");
        personalContributionLevels.add(personalContributionLevel);

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("Follow");
        personalContributionLevels.add(personalContributionLevel);

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("Assist");
        personalContributionLevels.add(personalContributionLevel);

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("Apply");
        personalContributionLevels.add(personalContributionLevel);

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("Create");
        personalContributionLevels.add(personalContributionLevel);

        personalContributionLevel = new PersonalContributionLevel();
        personalContributionLevel.setPersonalContributionLevelName("Design");
        personalContributionLevels.add(personalContributionLevel);


        personalContributeRepository.saveAll(personalContributionLevels);
    }

    @Test
    @Order(9)
    void saveProjectKPI(){
        List<ProjectKPILevel> projectKPILevels = new ArrayList<>();

        ProjectKPILevel projectKPILevel;

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("no Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("1-5 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("6-10 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("11-15 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("16-20 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName("21-30 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);

        projectKPILevel = new ProjectKPILevel();
        projectKPILevel.setProjectKPILevelName(">30 Kpi/Rec Point");
        projectKPILevels.add(projectKPILevel);


        projectKPILevelRepository.saveAll(projectKPILevels);
    }

    @Test
    @Order(10)
    void saveResponsiveLevel(){
        List<ResponsibilityProficiencyLevel> responsibilityProficiencyLevels = new ArrayList<>();

        ResponsibilityProficiencyLevel responsibilityProficiencyLevel;

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("No Responsivebility");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("Follow");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("Assist");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("Apply");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("Create");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("Design");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);

        responsibilityProficiencyLevel = new ResponsibilityProficiencyLevel();
        responsibilityProficiencyLevel.setResponsibilityProficiencyLevelName("initate");
        responsibilityProficiencyLevels.add(responsibilityProficiencyLevel);



        responsiveLevelRepository.saveAll(responsibilityProficiencyLevels);
    }

    @Test
    @Order(11)
    void saveRoleProficient(){
        List<RoleProficiencyLevel> roleProficiencyLevels = new ArrayList<>();

        RoleProficiencyLevel roleProficiencyLevel;

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Knowledge");
        roleProficiencyLevels.add(roleProficiencyLevel);

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Fundamental");
        roleProficiencyLevels.add(roleProficiencyLevel);

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Intermediate");
        roleProficiencyLevels.add(roleProficiencyLevel);

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Advance");
        roleProficiencyLevels.add(roleProficiencyLevel);

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Expert");
        roleProficiencyLevels.add(roleProficiencyLevel);

        roleProficiencyLevel = new RoleProficiencyLevel();
        roleProficiencyLevel.setRoleProficiencyLevelName("Master");
        roleProficiencyLevels.add(roleProficiencyLevel);


        roleProficientRepository.saveAll(roleProficiencyLevels);
    }

//    @Test
//    @Order(12)
//    void saveProfile() {
//        CompetencyRankingProfile profile;
//        profile = new CompetencyRankingProfile();
//        profile.setTitle("JavaFX and Oracle Engineer");
//        profile.setPeriod(periodRepository.findByPeriodName("S0-2021").get());
//        profile.setStatusType(statusRepository.findByStatusTypeName("In-Process").get());
//        profile.setEmployee(employeeRepository.findByCode("EMP00").get());
//
//        List<CompetencyRankingProfileDetail> profileDetails;
//        profileDetails = new ArrayList<>();
//        CompetencyRankingProfileDetail profileDetail;
//        CompetencyRankingPattern pattern = patternRepository.findByPatternName("Software Engineer Java").get();
//        profile.setPattern(pattern);
//        List<CompetencyRankingPatternDetail> patternDetails = pattern.getCompetencyRankingPatternDetail();
//        for (CompetencyRankingPatternDetail detail : patternDetails
//        ) {
//            profileDetail = new CompetencyRankingProfileDetail();
//            profileDetail.setCompetencyRankingProfile(profile);
//            profileDetail.setCompetencyRankingPatternDetail(detail);
//            profileDetail.setSource(dataSourceRepository.findByDataSourceName("Any where").get());
//            profileDetail.setProficientId(1);
//            profileDetail.setReviewPoint(1);
//            profileDetails.add(profileDetail);
//
//        }
//        profile.setCompetencyRankingProfileDetails(profileDetails);
//        profileRepository.save(profile);
//
//        profile = new CompetencyRankingProfile();
//        profile.setTitle("Java Trainning");
//        profile.setPeriod(periodRepository.findByPeriodName("S0-2021").get());
//        profile.setStatusType(statusRepository.findByStatusTypeName("In-Process").get());
//        profile.setEmployee(employeeRepository.findByCode("EMP00").get());
//
//        profileDetails = new ArrayList<>();
//        pattern = patternRepository.findByPatternName("Project Manager Product").get();
//        profile.setPattern(pattern);
//        for (CompetencyRankingPatternDetail detail : pattern.getCompetencyRankingPatternDetail()
//        ) {
//            profileDetail = new CompetencyRankingProfileDetail();
//            profileDetail.setCompetencyRankingProfile(profile);
//            profileDetail.setCompetencyRankingPatternDetail(detail);
//            profileDetail.setSource(dataSourceRepository.findByDataSourceName("Any where").get());
//            profileDetail.setProficientId(1);
//            profileDetail.setProficientId(1);
//            profileDetail.setReviewPoint(1);
//            profileDetails.add(profileDetail);
//        }
//        profile.setCompetencyRankingProfileDetails(profileDetails);
//        profileRepository.save(profile);
//
//    }

//    @Test
//    void deleteProfile() {
//       profileRepository.deleteAll();
//
//    }
//    @Test
//    void deletePattern() {
//        patternRepository.deleteAll();
//
//    }
//
//    @Test
//    void deleteComponent() {
//        componentRepository.deleteAll();
//
//    }
}
