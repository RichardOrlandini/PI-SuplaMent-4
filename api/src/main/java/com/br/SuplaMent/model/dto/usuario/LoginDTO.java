package com.br.SuplaMent.model.dto.usuario;

import com.br.SuplaMent.utils.security.tokenJwtDTO;

public record LoginDTO(tokenJwtDTO token, DetalhamentoUsuarioDTO user) {
}
