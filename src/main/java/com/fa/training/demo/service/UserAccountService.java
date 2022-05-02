package com.fa.training.demo.service;

import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;

import java.util.List;

public interface UserAccountService {
    void save(UserAccount user);

    UserAccount findByEmail(String email);

    UserAccount findById(int id);

    UserAccount findByPassword(String password);

    void deleteUser(UserAccount user);

    boolean existByUserName(String email);

    List<UserAccount> findAllEmployee(Integer page, Integer size);

    List<UserAccount> findAllEmployee();

}
