package com.fa.training.demo.service;

import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.repository.RoleRepository;
import com.fa.training.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService{
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(UserAccount user) {
        userAccountRepository.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByUserNameIgnoreCase(email);
    }

    @Override
    public UserAccount findById(int id) {
        return userAccountRepository.findById(id).orElse(null);
    }

    @Override
    public UserAccount findByPassword(String password) {
        return userAccountRepository.findByPassword(password);
    }

    @Override
    public void deleteUser(UserAccount user) {
        userAccountRepository.delete(user);
    }

    @Override
    public boolean existByUserName(String email) {
        return userAccountRepository.existsByUserName(email);
    }

    @Override
    public List<UserAccount> findAllEmployee(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userAccountRepository.findEmployeeAccount(pageRequest);
    }

    @Override
    public List<UserAccount> findAllEmployee() {
        return userAccountRepository.findEmployeeAccount();
    }


}
