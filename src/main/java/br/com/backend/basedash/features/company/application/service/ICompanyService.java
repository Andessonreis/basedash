package br.com.backend.basedash.features.company.application.service;

import br.com.backend.basedash.features.company.application.dto.CompanyPostRequestDto;

public interface ICompanyService {
  public String registerCompany(CompanyPostRequestDto companyDto);
}
