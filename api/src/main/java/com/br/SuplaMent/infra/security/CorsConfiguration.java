//package com.br.SuplaMent.infra.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/login")
//                .allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Content-Type")
//                .allowCredentials(true);
//    }
//}