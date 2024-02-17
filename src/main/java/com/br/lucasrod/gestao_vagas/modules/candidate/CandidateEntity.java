package com.br.lucasrod.gestao_vagas.modules.candidate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name="candidate")
public class CandidateEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;
    @Length(min = 8, max = 12, message = "O campo [Senha] deve conter entre (8) e (12) caracteres")
    private String password;
    private String description;
    private String curriculum;
    @CreationTimestamp 
    private LocalDateTime createdAt;

    //lombok -> criar getters e setters
}
