package com.br.lucasrod.gestao_vagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lucasrod.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    //CandidateEntity findByUsernameOrEmail(String username, String email); //Select
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email); //Select
    Optional<CandidateEntity> findByUsername(String username);
}
