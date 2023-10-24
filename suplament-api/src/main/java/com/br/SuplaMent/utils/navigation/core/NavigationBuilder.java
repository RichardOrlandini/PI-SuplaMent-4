package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.ApplicationEntity;
import com.br.SuplaMent.utils.aEntity.IEntity;

public class NavigationBuilder<E extends IEntity> extends ApplicationEntity {
    private Navigation navigation = new Navigation();

    public NavigationBuilder() {
    }

    public NavigationBuilder<E> next(IStrategy<? super E> activity) {
        this.navigation.addActivity(activity);
        return this;
    }

    public Navigation<E> build() {
        return this.navigation;
    }
}

