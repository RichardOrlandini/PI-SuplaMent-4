package com.br.SuplaMent.utils.navigation.core;


import com.br.SuplaMent.utils.aEntity.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class BusinessCaseBuilder<E extends IEntity> {
    public static final String SAVE = "SAVE_";
    public static final String UPTDATE = "UPDATE_";
    public static final String DELETE = "DELETE_";
    public static final String FILTER = "FILTER_";
    public static final String ACTIVATE = "ACTIVATE_";
    public static final String INACTIVATE = "INACTIVATE_";
    private static Map<String, Navigation<?>> listNavigations;
    private static Map<String, Navigator<?>> listNavigators;
    private static IAuthFactory authFactory = new DefaultAuthFactory();
    protected BusinessCase aCase;

    public BusinessCaseBuilder() {
    }

    @Autowired(
            required = true
    )
    public void setListNavigators(Map<String, Navigator<?>> listNavigators) {
        BusinessCaseBuilder.listNavigators = listNavigators;
    }
    @Autowired(
            required = true
    )
    public void setListNavigations(Map<String, Navigation<?>> listNavigations) {
        BusinessCaseBuilder.listNavigations = listNavigations;
    }

    @Autowired(
            required = false
    )
    public void setAuthFactory(IAuthFactory authFactory) {
        BusinessCaseBuilder.authFactory = authFactory;
    }

    public BusinessCase<E> defaultContext() {
        return new BusinessCase("DEFAULT_CONTEXT", authFactory);
    }

    public BusinessCase<E> withName(String name) {
        return new BusinessCase(name.trim().toUpperCase(), authFactory);
    }

    public BusinessCase<E> save(String name) {
        name = ((String)this.existingNavigation("SAVE_".concat(name.trim().toUpperCase())).orElse("SAVE_".concat("DEFAULT_CONTEXT"))).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    public BusinessCase<E> update(String name) {
        name = ((String)this.existingNavigation("UPDATE_".concat(name.trim().toUpperCase())).orElse("UPDATE_".concat("DEFAULT_CONTEXT"))).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    public BusinessCase<E> delete(String name) {
        name = ((String)this.existingNavigation("DELETE_".concat(name.trim().toUpperCase())).orElse("DEFAULT_CONTEXT")).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    public BusinessCase<E> filter(String name) {
        name = ((String)this.existingNavigation("FILTER_".concat(name.trim().toUpperCase())).orElse("DEFAULT_CONTEXT")).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    public BusinessCase<E> activate(String name) {
        name = ((String)this.existingNavigation("ACTIVATE_".concat(name.trim().toUpperCase())).orElse("ACTIVATE_".concat("BY_CODE"))).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    public BusinessCase<E> inactivate(String name) {
        name = ((String)this.existingNavigation("INACTIVATE_".concat(name.trim().toUpperCase())).orElse("INACTIVATE_".concat("BY_CODE"))).trim().toUpperCase();
        return new BusinessCase(name, authFactory);
    }

    private Optional<String> existingNavigation(String name) {
        return listNavigations.containsKey(name) ? Optional.of(name) : Optional.empty();
    }
}