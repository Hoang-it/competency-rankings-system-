package com.fa.training.demo.service;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;
import com.fa.training.demo.entities.UserAccount;

public interface EmployeeService {
    void save(Employee employee);

    void saveEmployeeContact(EmployeeContact employeeContact);

    void delete(Employee employee);

    Employee getEmployee(Integer id);

    EmployeeContact getEmployeeContact(Employee employee);

    EmployeeContact findEmployeeByEmail(String email);

}
