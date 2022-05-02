package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"competencyRankingProfiles", "userAccount"})
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String code;

    @Column
    private boolean gender;

    @Lob
    private byte[] avatar;

    @Column
    private boolean marital;

    @Column
    // Convert String to Date format when input type "date" in form
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @Column
    private LocalDate joinDate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "job_title"
            , foreignKey = @ForeignKey(name = "FK_Emloyee_JobTitle"))
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name = "job_rank"
            , foreignKey = @ForeignKey(name = "FK_Emloyee_JobRank"))
    private JobRank jobRank;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<EmployeeContact> employeeContacts;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeBusinessUnit> employeeBusinessUnits;

    @OneToMany(mappedBy = "employee")
    private List<CompetencyResult> competencyResults;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees;

    @OneToMany(mappedBy = "employee")
    private List<CompetencyRankingProfile> competencyRankingProfiles;

    @OneToMany(mappedBy = "employee")
    private List<CompetencyReviewRanking> competencyReviewRankings;

    /**
     * Convert byte[] data (image stored in database) to String Base64 to display in view
     * Because thymeleaf doesn't approve byte[] data
     */
    public String convertImageToString() {
        String encode = "";
        try {
            encode = Base64.getMimeEncoder().encodeToString(this.getAvatar());
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return encode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
