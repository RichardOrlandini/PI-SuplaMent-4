package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.IEntity;

public interface INavigationCase<E extends IEntity> extends IEntity {
    String DEFAULT_CONTEXT_NAME = "DEFAULT_CONTEXT";

    String getName();

    Result getResult();

    void suspendExecution();

    void suspendExecution(String var1);

    Boolean isSuspendExecution();

    void setContext(INavigatorContext var1);

    INavigatorContext getContext();
}
