package br.com.backend.basedash.features.company.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySimpleResponseDto {

    @JsonProperty("name")
    private String name;
}