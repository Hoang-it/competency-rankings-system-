package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Certificate extends BaseEntity{
    @Id
    private int certificateId;

    @Column
    private int certificateLevel;

    @Column
    private String certificateName;

}
