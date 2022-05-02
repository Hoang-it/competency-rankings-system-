package com.fa.training.demo.config;

import com.fa.training.demo.entities.*;
import com.fa.training.demo.repository.DataSourceRepository;
import com.fa.training.demo.repository.RoleRepository;
import com.fa.training.demo.repository.StatusRepository;
import com.fa.training.demo.repository.UserAccountRepository;
import com.fa.training.demo.service.EmployeeService;
import com.fa.training.demo.service.JobRankService;
import com.fa.training.demo.service.JobTitleService;
import com.fa.training.demo.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeedingLogin implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JobTitleService jobTitleService;

    @Autowired
    private JobRankService jobRankService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByRoleName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        if (roleRepository.findByRoleName("ROLE_MANAGER") == null) {
            roleRepository.save(new Role("ROLE_MANAGER"));
        }

        if (roleRepository.findByRoleName("ROLE_LEADER") == null) {
            roleRepository.save(new Role("ROLE_LEADER"));
        }
        List<String> job = Arrays.asList("Software Engineer", "Software Tester", "Project Manager", "Business", "Analyst", "Data Engineer", "AI Engineer");
        List<String> jobRank = Arrays.asList("Fresher", "Junior", "Principle", "Professional", "BRSE", "Senior");
        List<String> dataSource = Arrays.asList("Any where", "Owner", "Jira", "Aka link", "CTC", "PMC", "POD", "OB", "BOD");
        List<String> status = Arrays.asList("Pending", "Reviewed", "Submitted", "Closed", "Approved", "Rejected", "In-Progress", "Published");

        // Member account
        if (userAccountRepository.findByUserName("member@gmail.com") == null) {
            for (int i = 0; i < job.size(); i++) {
                if (jobTitleService.findById(i+1) != null) {
                    JobTitle jobTitle = new JobTitle();
                    jobTitle.setJobTitleName(job.get(i));
                    jobTitleService.save(jobTitle);
                }
            }
            for (int i = 0; i < jobRank.size(); i++) {
                if (jobRankService.findJobRank(i+1) != null) {
                    JobRank rank = new JobRank();
                    rank.setJobRankName(jobRank.get(i));
                    jobRankService.save(rank);
                }
            }
            for (int i = 0; i < dataSource.size(); i++) {
                if (dataSourceRepository.findById(i + 1) != null) {
                    DataSource data = new DataSource();
                    data.setDataSourceName(dataSource.get(i));
                    dataSourceRepository.save(data);
                }
            }
            for (int i = 0; i < status.size(); i++) {
                if (statusRepository.findById(i + 1) != null) {
                    StatusType stt = new StatusType();
                    stt.setStatusTypeName(status.get(i));
                    statusRepository.save(stt);
                }
            }
            for (int i = 0; i < 15; i++) {
                UserAccount user = new UserAccount();
                user.setUserName("member" + i + "@gmail.com");
                user.setPassword(passwordEncoder.encode("123123"));
                user.setActivated(i % 2 == 0);
                List<Role> roles = new ArrayList<>();
                roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
                user.setRoles(roles);

                Employee employee = new Employee();
                if (i < 7) {
                    JobTitle jobtitle = jobTitleService.findById(i+1);
                    employee.setJobTitle(jobtitle);
                } else if (i <= 13) {
                    JobTitle jobtitle = jobTitleService.findById(i-6);
                    employee.setJobTitle(jobtitle);
                } else {
                    JobTitle jobtitle = jobTitleService.findById(1);
                    employee.setJobTitle(jobtitle);
                }
                employee.setFirstName("Demo " + i);
                employee.setLastName("Checked");
                employee.setJobRank(jobRankService.findJobRank(1));
                employeeService.save(employee);

                user.setEmployee(employee);
                userAccountRepository.save(user);

                EmployeeContact contact = new EmployeeContact();
                contact.setEmployee(employee);
                employeeService.saveEmployeeContact(contact);
            }

            UserAccount user = new UserAccount();
            user.setUserName("member@gmail.com");
            user.setPassword(passwordEncoder.encode("123123"));
            user.setActivated(true);

            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            user.setRoles(roles);

            Employee employee = new Employee();
            employee.setJobTitle(jobTitleService.findById(1));
            employee.setJobRank(jobRankService.findJobRank(1));
            employeeService.save(employee);

            user.setEmployee(employee);
            userAccountRepository.save(user);

            EmployeeContact contact = new EmployeeContact();
            contact.setEmployee(employee);
            employeeService.saveEmployeeContact(contact);

        }

        // Admin account
        if (userAccountRepository.findByUserName("admin@gmail.com") == null) {
            UserAccount admin = new UserAccount();
            admin.setUserName("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123123"));
            admin.setActivated(true);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            admin.setRoles(roles);

            Employee employee = new Employee();
            employee.setJobTitle(jobTitleService.findById(1));
            employee.setJobRank(jobRankService.findJobRank(1));
            employeeService.save(employee);

            admin.setEmployee(employee);
            userAccountRepository.save(admin);

            EmployeeContact contact = new EmployeeContact();
            contact.setEmployee(employee);
            employeeService.saveEmployeeContact(contact);
        }

        //leader Account
        if (userAccountRepository.findByUserName("leader@gmail.com") == null) {
            UserAccount leader = new UserAccount();
            leader.setUserName("leader@gmail.com");
            leader.setPassword(passwordEncoder.encode("123123"));
            leader.setActivated(true);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            roles.add(roleRepository.findByRoleName("ROLE_LEADER"));
            leader.setRoles(roles);

            Employee employee = new Employee();
            employee.setJobTitle(jobTitleService.findById(1));
            employee.setJobRank(jobRankService.findJobRank(1));
            employeeService.save(employee);

            leader.setEmployee(employee);
            userAccountRepository.save(leader);

            EmployeeContact contact = new EmployeeContact();
            contact.setEmployee(employee);
            employeeService.saveEmployeeContact(contact);
        }

        // Manager account
        if (userAccountRepository.findByUserName("manager@gmail.com") == null) {
            Period period = new Period();
            period.setPeriodName("S1 2020");
            periodService.save(period);
            period = new Period();
            period.setPeriodName("S2 2020");
            periodService.save(period);

            UserAccount manager = new UserAccount();
            manager.setUserName("manager@gmail.com");
            manager.setPassword(passwordEncoder.encode("123123"));
            manager.setActivated(true);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_MANAGER"));
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            manager.setRoles(roles);

            Employee employee = new Employee();
            employee.setJobTitle(jobTitleService.findById(1));
            employee.setJobRank(jobRankService.findJobRank(1));
            employeeService.save(employee);

            manager.setEmployee(employee);
            userAccountRepository.save(manager);

            EmployeeContact contact = new EmployeeContact();
            contact.setEmployee(employee);
            employeeService.saveEmployeeContact(contact);

        }


    }
}
