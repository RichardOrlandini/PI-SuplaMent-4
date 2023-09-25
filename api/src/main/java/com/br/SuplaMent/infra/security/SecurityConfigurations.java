package com.br.SuplaMent.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;


    @Bean
    public SecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        return http
                .cors().configurationSource(request -> getCorsConfiguration())
                .and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                   req.requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN");
                   req.requestMatchers(HttpMethod.DELETE, "/usuarios").hasRole("ADMIN");

                    req.requestMatchers("/usuarios/*").permitAll();
                    req.requestMatchers("/produtos/*").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/estoque").hasRole("ESTOQUISTA");
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedOrigin("*"); // Allow all origins
        return config;
    }
    //    @Bean testar esse aqui
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

    // caso queria adicionar a rota de cadastro, manipular acima
//    @Bean
//    public UserDetailsService userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("12345"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails estoquista = User.builder()
//                .username("estoquista")
//                .password(encoder.encode("12345"))
//                .roles("ESTOQUISTA")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, estoquista);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

