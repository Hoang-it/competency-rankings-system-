package com.fa.training.demo.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Token {

    // Change 1 hour = 3600*1000 milli seconds
    // hh:mm:ss.000
    private static final long HOUR = 24*60*60*1000;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String token;

    private Date expireDate = new Date();

    @OneToOne (targetEntity = UserAccount.class)
    @JoinColumn(nullable = false, name = "user_id")
    private UserAccount user;

    public Token(UserAccount user) {
        this.user = user;
        token = UUID.randomUUID().toString();
        expireDate = new Date(expireDate.getTime() + HOUR);
    }
}
