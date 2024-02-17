package com.br.lucasrod.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.lucasrod.gestao_vagas.exceptions.UserFoundException;
import com.br.lucasrod.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.br.lucasrod.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@SpringBootApplication
@Service
public class CreateCandidateUserCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) ->{
                throw new UserFoundException();
            });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        
        return this.candidateRepository.save(candidateEntity);
    }
}
