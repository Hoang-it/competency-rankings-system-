package com.fa.training.demo.service;

import com.fa.training.demo.entities.Token;
import com.fa.training.demo.entities.UserAccount;
import com.fa.training.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Token findByUser(UserAccount user) {
        return tokenRepository.findByUser(user);
    }

    @Override
    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }
}
