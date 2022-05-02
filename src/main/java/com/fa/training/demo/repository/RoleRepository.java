package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleId(int roleId);

    Role findByRoleName(String roleName);
}
