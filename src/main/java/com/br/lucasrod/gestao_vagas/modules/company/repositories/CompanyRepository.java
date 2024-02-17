package com.br.lucasrod.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lucasrod.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
    //CandidateEntity findByUsernameOrEmail(String username, String email); //Select
    
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email); //Select

    Optional<CompanyEntity> findByUsername(String username); //Select
}
//