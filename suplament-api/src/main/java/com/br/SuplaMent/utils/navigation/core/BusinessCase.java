package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.IEntity;

public class BusinessCase<E extends IEntity> implements INavigationCase<E> {
    private Result result;
    private String name;
    private Boolean suspend = false;
    private E entity;
    private INavigatorContext context;

    public BusinessCase() {
        this.result = new Result();
        this.context = new NavigatorContext();
    }

    public BusinessCase(String name) {
        this.name = name;
        this.result = new Result();
        this.context = new NavigatorContext();
    }

    public BusinessCase(String name, IAuthFactory authFactory) {
        this.name = name;
        this.result = new Result();
        this.context = new NavigatorContext();
        this.context.setAttributes(authFactory.authContextParams());
    }

    public String getName() {
        return this.name;
    }

    public Result getResult() {
        return this.result;
    }

    public void suspendExecution() {
        this.suspend = true;
        this.getResult().setError();
    }

    public Boolean isSuspendExecution() {
        return this.suspend;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public E getEntity() {
        return this.entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void setContext(INavigatorContext context) {
        if (this.context == null) {
            this.context = context;
        } else {
            this.context.setAttributes(context.getAttributes());
        }
    }

    public INavigatorContext getContext() {
        return this.context;
    }

    public void suspendExecution(String message) {
        this.suspendExecution();
        this.result.setMessage(message);
    }
}