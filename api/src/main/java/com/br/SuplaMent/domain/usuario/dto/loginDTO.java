package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.infra.security.tokenJwtDTO;

public record loginDTO(tokenJwtDTO token, detalhamentoUsuarioDTO user) {
}
