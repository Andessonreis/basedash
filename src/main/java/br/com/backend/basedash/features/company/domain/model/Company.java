package br.com.backend.basedash.features.company.domain.model;

import java.util.UUID;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome pode ter no máximo 100 caracteres.")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Column(unique = true, nullable = false)
    private String cnpj;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Informe um email válido.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private Double latitude;
    private Double longitude;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
