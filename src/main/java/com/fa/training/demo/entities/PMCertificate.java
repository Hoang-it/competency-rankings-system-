package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PMCertificate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pMCertificateId;

    @Column
    private int pMCertificateLevel;

    @Column
    private String pMCertificateName;

}
