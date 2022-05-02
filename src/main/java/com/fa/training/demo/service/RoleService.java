package com.fa.training.demo.service;

import com.fa.training.demo.entities.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
