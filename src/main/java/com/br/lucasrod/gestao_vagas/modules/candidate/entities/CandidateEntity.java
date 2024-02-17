package com.br.lucasrod.gestao_vagas.modules.candidate.entities;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name="candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Auto-increment
    private UUID id;
    private String name;
    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 100, message = "O campo [Senha] deve conter entre (10) e (12) caracteres")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do Candidato")
    private String password;
    private String description;
    private String curriculum;
    
    @CreationTimestamp 
    private LocalDateTime createdAt;

    //lombok -> criar getters e setters
}
