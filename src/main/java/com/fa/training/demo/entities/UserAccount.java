package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
//@JsonIgnoreProperties(value = {"employee"})
public class UserAccount extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private boolean isActivated;

    @Column
    private LocalDate lastLogged;

    @OneToOne
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
