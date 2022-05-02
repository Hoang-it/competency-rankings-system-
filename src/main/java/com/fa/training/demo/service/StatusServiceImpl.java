package com.fa.training.demo.service;

import com.fa.training.demo.entities.StatusType;
import com.fa.training.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository repository;

    @Override
    public StatusType getStatus(Integer id) {
        return repository.getOne(id);
    }
}
