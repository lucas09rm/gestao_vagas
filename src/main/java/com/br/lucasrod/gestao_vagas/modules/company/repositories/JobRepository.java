package com.br.lucasrod.gestao_vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lucasrod.gestao_vagas.modules.company.entities.JobEntity;
import java.util.List;


public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter); // SELECT LIKE %filter%
}
