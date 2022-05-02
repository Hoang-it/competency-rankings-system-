package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class JobRole extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobRoleId;

    @Column
    private String jobRoleName;

}
