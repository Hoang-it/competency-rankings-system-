package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class EmployeeBusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeBusinessUnitId;

    @Column
    private LocalDate registeredDate;

    @Column
    private LocalDate commecement;

    @Column
    private LocalDate expiry;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private BusinessUnit businessUnit;

}
