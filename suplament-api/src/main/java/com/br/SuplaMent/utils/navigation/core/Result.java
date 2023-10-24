package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.ApplicationEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Result extends ApplicationEntity {
    public static final String RESULT_KEY = "result";
    public static final String RESULTS_KEY = "results";
    private Map<String, Object> params = new HashMap();
    private String message;
    private boolean error;

    public Result() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasError() {
        return this.error;
    }

    public void setError() {
        this.error = true;
    }

    public void addEntity(String key, Object entity) {
        Optional.ofNullable(entity).ifPresent((e) -> {
            this.params.put(key, entity);
        });
    }

    public void addEntity(Object entity) {
        Optional.ofNullable(entity).ifPresent((e) -> {
            this.params.put("result", entity);
        });
    }

    public void addEntities(Stream<?> result) {
        Optional.ofNullable(result).ifPresent((r) -> {
            this.params.put("results", r);
        });
    }

    public <R> R getEntity(String key) {
        return (R) (R) Optional.ofNullable(this.params.get(key));
    }

    public <R> R getEntity() {
        return (R) (R) Optional.ofNullable(this.params.get("result"));
    }

    public <R> R getEntities() {
        return (R) Optional.ofNullable(this.params.get("results"));
    }
}
