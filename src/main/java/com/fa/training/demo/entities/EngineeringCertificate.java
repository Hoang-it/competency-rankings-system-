package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EngineeringCertificate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int engineeringCertificateId;

    @Column
    private int engineeringCertificateLevel;

    @Column
    private String engineeringCertificateName;

}
