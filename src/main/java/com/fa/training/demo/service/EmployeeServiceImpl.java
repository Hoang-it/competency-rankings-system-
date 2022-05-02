package com.fa.training.demo.service;

import com.fa.training.demo.entities.Employee;
import com.fa.training.demo.entities.EmployeeContact;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.repository.EmployeeContactRepository;
import com.fa.training.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeContactRepository employeeContactRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void saveEmployeeContact(EmployeeContact employeeContact) {
        employeeContactRepository.save(employeeContact);
    }

    /**
     * Get information of employee. For example: name, gender, avatar...
     */
    @Override
    public Employee getEmployee(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public EmployeeContact getEmployeeContact(Employee employee) {
        return employeeContactRepository.findByEmployee(employee);
    }

    @Override
    public EmployeeContact findEmployeeByEmail(String email) {
        return employeeContactRepository.findEmployeeContactByEmail(email);
    }
}
