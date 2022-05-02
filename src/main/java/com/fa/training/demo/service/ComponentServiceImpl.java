package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyComponent;
import com.fa.training.demo.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComponentServiceImpl implements ComponentService{
    @Autowired
    ComponentRepository componentRepository;

    @Override
    public CompetencyComponent findById(int id) {
        return componentRepository.getOne(id);
    }

    @Override
    public List<CompetencyComponent> findAll() {
        return componentRepository.findAll();
    }
}
