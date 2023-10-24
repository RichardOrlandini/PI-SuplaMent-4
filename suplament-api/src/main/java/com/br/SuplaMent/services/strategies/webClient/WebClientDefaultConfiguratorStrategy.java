package com.br.SuplaMent.services.strategies.webClient;

import com.br.SuplaMent.utils.navigation.core.INavigationCase;
import com.br.SuplaMent.utils.navigation.core.IStrategy;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebClientDefaultConfiguratorStrategy implements IStrategy<WebClientDefaultConfig> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process(WebClientDefaultConfig aEntity, INavigationCase<WebClientDefaultConfig> aCase) {
        final WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);

        final WebClientOptions webClientOptions = aEntity.getWebClientOptions();

        try {
            logger.info("[WEB_CLIENT] Configurando o Web Client...");

            this.configureWebClientOptions(webClient, webClientOptions);

            aCase.getResult().addEntity(webClient);

        } catch (Exception e) {
            logger.error("[WEB_CLIENT] Erro ao tentar configurar o Web Client.");

            e.printStackTrace();

            final String message = e.getMessage();

            aCase.suspendExecution(message);
        }
    }

    private void configureWebClientOptions(final WebClient webClient, WebClientOptions webClientOptions) {
        webClient.getCookieManager().setCookiesEnabled(
                webClientOptions.getCookiesEnabled()
        );

        webClient.getOptions().setTimeout(
                webClientOptions.getTimeout()
        );
        webClient.getOptions().setUseInsecureSSL(
                webClientOptions.getUseInsecureSsl()
        );
        webClient.getOptions().setRedirectEnabled(
                webClientOptions.getRedirectEnabled()
        );
        webClient.getOptions().setCssEnabled(
                webClientOptions.getCssEnabled()
        );
        webClient.getOptions().setJavaScriptEnabled(
                webClientOptions.getJavaScriptEnabled()
        );
        webClient.getOptions().setThrowExceptionOnScriptError(
                webClientOptions.getThrowExceptionOnScriptError()
        );
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(
                webClientOptions.getThrowExceptionOnFailingStatusCode()
        );
        webClient.getOptions().setDownloadImages(
                webClientOptions.getDownloadImages()
        );
        webClient.getOptions().setGeolocationEnabled(
                webClientOptions.getGeolocationEnabled()
        );
        webClient.getOptions().setAppletEnabled(
                webClientOptions.getAppletEnabled()
        );
    }
}
