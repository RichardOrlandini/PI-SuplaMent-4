package com.br.SuplaMent.domain.pessoa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto (@NotBlank @Email String email,  @NotBlank String senha){
}
