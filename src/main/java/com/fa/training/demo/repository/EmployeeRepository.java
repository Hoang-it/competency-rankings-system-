package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;
import com.fa.training.demo.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByCode(String code);

}
