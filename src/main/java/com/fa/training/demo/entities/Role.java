package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"userAccountList"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<UserAccount> userAccountList;

    public Role(String name) {
        this.roleName = name;
    }

    public Role() {
    }
}
