package com.example.scottishhills.dao;

import com.example.scottishhills.model.ScottishHill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScottishHillRepository extends
        JpaRepository<ScottishHill, Long>, JpaSpecificationExecutor<ScottishHill> {

}
