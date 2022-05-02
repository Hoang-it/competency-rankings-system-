package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"employee"})
public class EmployeeContact extends BaseEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int employeeContactId;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column
    private String stateId;

    @ManyToOne
    private Employee employee;
}
