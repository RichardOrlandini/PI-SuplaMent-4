package com.br.SuplaMent.services.strategies.webClient;

import com.br.SuplaMent.utils.aEntity.ApplicationEntity;
import com.br.SuplaMent.utils.aEntity.IEntity;

public class WebClientDefaultConfig extends ApplicationEntity implements IEntity {

    private WebClientOptions options;
    public WebClientDefaultConfig() {
        this.options = new WebClientOptions();
    }
    public WebClientOptions getWebClientOptions() {
        return options;
    }
    public void setWebClientOptions(WebClientOptions options) {
        this.options = options;
    }
}
