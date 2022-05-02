package com.fa.training.demo.service;

import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUserName(email);
        if (userAccount == null){
            throw new UsernameNotFoundException("user not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        List<Role> roles = userAccount.getRoles();

        for(Role e: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(e.getRoleName()));
        }

        UserDetails userDetails = User.withUsername(userAccount.getUserName())
                .password(userAccount.getPassword())
                .disabled(!userAccount.isActivated())
                .authorities(grantedAuthorities).build();
        return userDetails;
    }
}
