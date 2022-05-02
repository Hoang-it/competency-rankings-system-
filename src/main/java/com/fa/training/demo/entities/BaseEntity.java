package com.fa.training.demo.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column
    @CreationTimestamp
    private LocalDate created;

    @Column
    private int createdBy;

    @Column
    @UpdateTimestamp
    private LocalDate modified;

    @Column
    private int modifiedBy;

    @Column
    private int versionNo;

}
