package com.br.SuplaMent.infra.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Long id;
    private String email;

    public static JwtResponse getUser(Claims jwtClaims) {

        try {
            return JwtResponse
                    .builder()
                    .id((Long) jwtClaims.get("id"))
                    .email((String) jwtClaims.get("email"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
