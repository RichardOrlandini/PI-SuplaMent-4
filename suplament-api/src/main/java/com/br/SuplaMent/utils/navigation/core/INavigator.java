package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.IEntity;

public interface INavigator<E extends IEntity> extends IEntity {
    void run(IEntity var1, INavigationCase var2);
}