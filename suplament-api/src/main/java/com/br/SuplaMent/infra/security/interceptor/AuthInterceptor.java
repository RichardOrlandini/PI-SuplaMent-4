package com.br.SuplaMent.infra.security.interceptor;

import com.br.SuplaMent.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    private static String AUTHORIZATION = "Authorization";

    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (isOptions(request)) {
                return true;
            }

            var authorization = request.getHeader(AUTHORIZATION);
            tokenService.isAutorizado(authorization);
            return true;

        }

        private boolean isOptions(HttpServletRequest request) {
            return HttpMethod.OPTIONS.name().equals(request.getMethod());
        }

}
