package br.com.backend.basedash.features.company.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.backend.basedash.features.company.application.dto.CompanyPostRequestDto;

import br.com.backend.basedash.infra.util.ObjectMapperUtil;

@Service
public class CompanyService implements ICompanyService {

  @Value("${supabase.url}")
  private String supabaseUrl;

  @Value("${supabase.key}")
  private String supabaseKey;

  @Autowired
  private ObjectMapperUtil objectMapperUtil;

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public String registerCompany(CompanyPostRequestDto companydDto) {
      System.err.println("Registrando a empresa service");
  
      return Optional.of(companydDto)
              .map(c -> objectMapperUtil.objectToJson(c))
              .map(json -> {
                  HttpHeaders headers = new HttpHeaders();
                  headers.setContentType(MediaType.APPLICATION_JSON);
                  headers.set("apikey", supabaseKey);
                  headers.set("Authorization", "Bearer " + supabaseKey);
                  return new HttpEntity<>(json, headers);
              })
              .map(entity -> restTemplate.postForEntity(supabaseUrl + "/rest/v1/companies", entity, String.class))
              .filter(response -> response.getStatusCode() == HttpStatus.CREATED)
              .map(response -> "Empresa criada com sucesso!")
              .orElseThrow(() -> new RuntimeException("Erro durante o registro da empresa"));
  }
  
}
