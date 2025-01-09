package br.com.backend.basedash.features.company.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyPostRequestDto(
    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome pode ter no máximo 100 caracteres.")
    String name,

    @NotBlank(message = "O CNPJ é obrigatório.")
    String cnpj,

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Informe um email válido.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
    String password

) {}

