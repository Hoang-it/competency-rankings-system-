package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeContactRepository extends JpaRepository<EmployeeContact, Integer> {
    EmployeeContact findByEmployee(Employee employee);
    EmployeeContact findEmployeeContactByEmail(String email);
}
