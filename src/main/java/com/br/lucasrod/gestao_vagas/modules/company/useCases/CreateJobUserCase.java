package com.br.lucasrod.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lucasrod.gestao_vagas.exceptions.CompanyNotFoundException;
import com.br.lucasrod.gestao_vagas.modules.company.entities.JobEntity;
import com.br.lucasrod.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.br.lucasrod.gestao_vagas.modules.company.repositories.JobRepository;

@SuppressWarnings("null")
@Service
public class CreateJobUserCase {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity){  
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(
            ()-> {throw new CompanyNotFoundException(); 
            });
        return this.jobRepository.save(jobEntity);
    }
}
