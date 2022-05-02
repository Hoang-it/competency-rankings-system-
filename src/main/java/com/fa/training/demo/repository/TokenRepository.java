package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Token;
import com.fa.training.demo.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);

    Token findByUser(UserAccount user);
}
