package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyComponent;
import com.fa.training.demo.entities.CompetencyComponentDetail;
import com.fa.training.demo.repository.ComponentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComponentDetailServiceImpl implements ComponentDetailService{
    @Autowired
    private ComponentDetailRepository componentDetailRepository;
    public CompetencyComponentDetail findById(int id){
        return componentDetailRepository.getOne(id);
    }

    @Override
    public List<CompetencyComponentDetail> findComponentDetailByComponent(CompetencyComponent component) {
        return componentDetailRepository.findCompetencyComponentDetailByCompetencyComponent(component);
    }

    @Override
    public CompetencyComponentDetail findByName(String name) {
        return componentDetailRepository.findByComponentDetailName(name);
    }

    @Override
    public CompetencyComponentDetail save(CompetencyComponentDetail componentDetail) {
        return componentDetailRepository.save(componentDetail);
    }
}
