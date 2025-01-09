package br.com.backend.basedash.controllers.util;

import java.util.HashMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ResultError {

  public static HashMap<String, String> getResultErrors(BindingResult result) {

    HashMap<String, String> erros = new HashMap<>();

    for (FieldError erro : result.getFieldErrors())
      erros.put(erro.getField(), erro.getDefaultMessage());

    return erros;

  }
}
