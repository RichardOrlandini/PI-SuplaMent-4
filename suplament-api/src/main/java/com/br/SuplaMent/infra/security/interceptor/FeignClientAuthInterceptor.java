package com.br.SuplaMent.infra.security.interceptor;

import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
public class FeignClientAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    @Override
    public void apply(RequestTemplate requestTemplate) {
        var currentRequest = this.getCurrentRequest();
        requestTemplate
                .header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION));
    }

    private HttpServletRequest getCurrentRequest() {

        try {
            return  ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationExcepetion("A Requisição atual não foi processada.");
        }

    }
}
