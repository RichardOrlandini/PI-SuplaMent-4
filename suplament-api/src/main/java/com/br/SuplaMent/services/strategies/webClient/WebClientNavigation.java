package com.br.SuplaMent.services.strategies.webClient;

import com.br.SuplaMent.utils.navigation.core.NavigationBuilder;
import com.br.SuplaMent.utils.navigation.core.Navigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientNavigation {
    public final String CONFIGURE_DEFAULT_WEB_CLIENT = "CONFIGURE_DEFAULT_WEB_CLIENT";
    @Autowired
    private WebClientDefaultConfiguratorStrategy webClientDefaultConfiguratorStrategy;

    @Bean(name = CONFIGURE_DEFAULT_WEB_CLIENT)
    public Navigation<WebClientDefaultConfig> getDefaultWebClient() {
        return new NavigationBuilder<WebClientDefaultConfig>()
                .next(webClientDefaultConfiguratorStrategy)
                .build();
    }
}
