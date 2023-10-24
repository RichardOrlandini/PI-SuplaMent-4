package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.IEntity;

public interface IStrategy<T extends IEntity> extends IEntity {
    void process(T var1, INavigationCase<T> var2);
}
