package com.fa.training.demo.security;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;
import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeContactRepository employeeContactRepository;
    @Autowired
    private JobRankRepository jobRankRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException{
        OidcUser oidcUser =  super.loadUser(userRequest);
        Map attributes =oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        Employee employee = new Employee();
        UserAccount userAccount = new UserAccount();
        EmployeeContact employeeContact1 = employeeContactRepository.findEmployeeContactByEmail(email);
        if(employeeContact1 == null) {
            employee.setJobRank(jobRankRepository.findByJobRankName("Fresher"));
            employeeRepository.save(employee);
            EmployeeContact employeeContact = new EmployeeContact();
            employeeContact.setEmail(email);
            employeeContact.setEmployee(employee);
            employeeContactRepository.save(employeeContact);

            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));

            userAccount.setUserName(email);
            userAccount.setActivated(true);
            userAccount.setRoles(roles);
            userAccount.setEmployee(employee);

            updateUser(userAccount);
        }

        UserAccount userAccount1 = userAccountRepository.findByUserName(email);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<Role> roles1 = userAccount1.getRoles();
        for(Role role : roles1){
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(userAccount1.getUserName(),grantedAuthorityList);
        return customUserDetails;
    }

    private void updateUser (UserAccount userAccount){
        UserAccount userAccount1 = userAccountRepository.findByUserName(userAccount.getUserName());
        if(userAccount1 == null) {
            userAccount1 = new UserAccount();
        }
        userAccount1.setUserName(userAccount.getUserName());
        userAccount1.setRoles(userAccount.getRoles());
        userAccount1.setActivated(userAccount.isActivated());
        userAccount1.setEmployee(userAccount.getEmployee());
        userAccountRepository.save(userAccount1);

    }
}
