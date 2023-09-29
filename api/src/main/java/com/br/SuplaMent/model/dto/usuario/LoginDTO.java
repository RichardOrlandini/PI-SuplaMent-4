package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.infra.security.tokenJwtDTO;

public record LoginDTO(tokenJwtDTO token, DetalhamentoUsuarioDTO user) {
}
