package br.com.backend.basedash.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.backend.basedash.controllers.util.ResultError;
import br.com.backend.basedash.features.company.application.dto.CompanyPostRequestDto;
import br.com.backend.basedash.features.company.application.service.ICompanyService;
import br.com.backend.basedash.infra.util.ObjectMapperUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    ObjectMapperUtil objectMapperUtil;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CompanyPostRequestDto companyDto, BindingResult result) {
        
        return result.hasErrors()
            ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultError.getResultErrors(result))
            : ResponseEntity.status(HttpStatus.CREATED)
                .body(this.companyService.registerCompany(companyDto));
    }

}
