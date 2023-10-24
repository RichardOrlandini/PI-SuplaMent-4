package com.br.SuplaMent.utils.navigation.core;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(
        proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = "prototype"
)
public class NavigatorContext implements INavigatorContext {
    private Map<String, Object> params = new HashMap();

    public NavigatorContext() {
    }

    public Map<String, Object> getParams() {
        return new HashMap(this.params);
    }

    public void setAttribute(String key, Object attribute) {
        this.params.put(key, attribute);
    }

    public <R> R getAttribute(String key) {
        return (R) this.params.get(key);
    }

    public Map<String, Object> getAttributes() {
        return this.params;
    }

    /** @deprecated */
    @Deprecated
    public void setAttributes(Map<String, Object> attributes) {
        this.params.putAll(attributes);
    }
}