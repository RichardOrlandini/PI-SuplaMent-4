package com.br.SuplaMent.services.strategies.webClient;

public class WebClientOptions {
    private Boolean cookiesEnabled = true;
    private Boolean useInsecureSsl = true;
    private Integer timeout = 20000;
    private Boolean redirectEnabled = true;
    private Boolean cssEnabled = false;
    private Boolean javaScriptEnabled = false;
    private Boolean throwExceptionOnScriptError = false;
    private Boolean throwExceptionOnFailingStatusCode = false;
    private Boolean downloadImages = false;
    private Boolean geolocationEnabled = false;
    private Boolean appletEnabled = false;

    public Boolean getCookiesEnabled() {
        return cookiesEnabled;
    }

    public void setCookiesEnabled(Boolean cookiesEnabled) {
        this.cookiesEnabled = cookiesEnabled;
    }

    public Boolean getUseInsecureSsl() {
        return useInsecureSsl;
    }

    public void setUseInsecureSsl(Boolean useInsecureSsl) {
        this.useInsecureSsl = useInsecureSsl;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getRedirectEnabled() {
        return redirectEnabled;
    }

    public void setRedirectEnabled(Boolean redirectEnabled) {
        this.redirectEnabled = redirectEnabled;
    }

    public Boolean getCssEnabled() {
        return cssEnabled;
    }

    public void setCssEnabled(Boolean cssEnabled) {
        this.cssEnabled = cssEnabled;
    }

    public Boolean getJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(Boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    public Boolean getThrowExceptionOnScriptError() {
        return throwExceptionOnScriptError;
    }

    public void setThrowExceptionOnScriptError(Boolean throwExceptionOnScriptError) {
        this.throwExceptionOnScriptError = throwExceptionOnScriptError;
    }

    public Boolean getThrowExceptionOnFailingStatusCode() {
        return throwExceptionOnFailingStatusCode;
    }

    public void setThrowExceptionOnFailingStatusCode(Boolean throwExceptionOnFailingStatusCode) {
        this.throwExceptionOnFailingStatusCode = throwExceptionOnFailingStatusCode;
    }

    public Boolean getDownloadImages() {
        return downloadImages;
    }

    public void setDownloadImages(Boolean downloadImages) {
        this.downloadImages = downloadImages;
    }

    public Boolean getGeolocationEnabled() {
        return geolocationEnabled;
    }

    public void setGeolocationEnabled(Boolean geolocationEnabled) {
        this.geolocationEnabled = geolocationEnabled;
    }

    public Boolean getAppletEnabled() {
        return appletEnabled;
    }

    public void setAppletEnabled(Boolean appletEnabled) {
        this.appletEnabled = appletEnabled;
    }
}
