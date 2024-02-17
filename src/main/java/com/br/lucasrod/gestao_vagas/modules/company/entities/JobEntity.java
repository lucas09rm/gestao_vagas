package com.br.lucasrod.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity(name="job")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Auto-increment
    private UUID id;

    @NotBlank(message = "Campo obrigatório")      
    @Schema (example = "Vaga para pessoa desenvoldora senior")
    private String description;   

    @NotBlank(message = "Campo obrigatório")       
    @Schema (example = "GymPass, VA, VR e PS")
    private String benefits;  

    @NotBlank(message = "Campo obrigatório")  
    @Schema (example = "Senior")
    private String level;

    @ManyToOne()
    @JoinColumn(name="company_id", insertable = false, updatable = false)    //update e insert (false)- notation apenas de relacionamento sem persistencia   
    private CompanyEntity companyEntity;

    @Column(name="company_id", nullable=false)
    private UUID companyId;

    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
