package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.infra.security.DTOtokenJWT;

public record DtoLogin (DTOtokenJWT token, DtoDetalhamentoUsuario user) {
}
