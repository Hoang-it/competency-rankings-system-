package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class BusinessUnit extends BaseEntity{
    @Id
    private int businessUnitId;

    @Column
    private String businessUnitName;

    @OneToMany(mappedBy = "businessUnit")
    private List<EmployeeBusinessUnit> employeeBusinessUnits;

}
