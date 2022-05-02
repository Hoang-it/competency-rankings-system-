package com.fa.training.demo.service;


import com.fa.training.demo.entities.Token;
import com.fa.training.demo.entities.UserAccount;


public interface TokenService {

    void save(Token token);

    Token findByToken(String token);

    Token findByUser(UserAccount user);

    void deleteToken(Token token);
}
