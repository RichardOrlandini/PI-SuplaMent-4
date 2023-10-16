package com.br.SuplaMent.infra.security;


import com.br.SuplaMent.infra.exception.AuthenticationException;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class TokenService {
    @Value("{app-config.secrets.api-secret}")
    private String secret;

    private static final String BEARER = "bearer";

    public void isAutorizado(String token) {
        var acessToken = extractToken(token);

        try {
            var claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(acessToken)
                    .getBody();
            var user = JwtResponse.getUser(claims);
            if (isEmpty(user) || isEmpty(user.getId())) {
                throw new AuthenticationException("O usuario não é valido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("Erro no processamento do token");
        }
    }

    private String extractToken(String token) {

        if (isEmpty(token)) {
            throw new AuthenticationException("Token não informado");
        }

        if (token.contains(BEARER)) {
            token = token.toLowerCase();
            token = token.replace(BEARER, Strings.EMPTY);
        }
        return token;
    }
}