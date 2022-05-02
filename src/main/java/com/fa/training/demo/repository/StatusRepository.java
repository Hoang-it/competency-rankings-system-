package com.fa.training.demo.repository;

import com.fa.training.demo.entities.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusType, Integer> {
    Optional<StatusType> findByStatusTypeName(String name);
}
