package com.fa.training.demo.repository;

import com.fa.training.demo.entities.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Integer> {
    Optional<DataSource> findByDataSourceName(String name);
}
