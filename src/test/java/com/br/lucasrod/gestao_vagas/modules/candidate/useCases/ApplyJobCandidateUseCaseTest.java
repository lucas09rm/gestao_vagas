package com.br.lucasrod.gestao_vagas.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.lucasrod.gestao_vagas.exceptions.UserNotFoundException;
import com.br.lucasrod.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.br.lucasrod.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.br.lucasrod.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.br.lucasrod.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.br.lucasrod.gestao_vagas.modules.company.entities.JobEntity;
import com.br.lucasrod.gestao_vagas.modules.company.repositories.JobRepository;

@SuppressWarnings("null")
@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks //Injetar classes
    private ApplyJobCandidateUseCase applyJobCandidateUseCase; 

    @Mock //Injetar dependencia da classe que sera injetada
    private CandidateRepository candidateRepository;

    @Mock //Injetar dependencia da classe que sera injetada
    private JobRepository jobRepository;

    @Mock //Injetar dependencia da classe que sera injetada
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should ot be able to apply job with candidate not found")
    public void should_ot_be_able_to_apply_job_with_candidate_not_found(){
        try {
            applyJobCandidateUseCase.execute(null,null);
        } catch (Exception e) {
            assertSame(UserNotFoundException.class, e.getClass());
        }
        
    }

    @Test
    public void should_ot_be_able_to_create_a_new_apply_job(){

        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();
        
        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();
        
        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        //asserThat(result).hasFiledProperty("id");
        assertNotNull(result.getId());
    }

}
