package com.br.SuplaMent.utils.navigation.core;

import com.br.SuplaMent.utils.aEntity.ApplicationEntity;
import com.br.SuplaMent.utils.aEntity.IEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Navigation<E extends IEntity> extends ApplicationEntity {
    private List<IStrategy<? super E>> activities = new ArrayList();

    public Navigation() {
    }

    public void addActivity(IStrategy<? super E> activity) {
        this.activities.add(activity);
    }

    public List<IStrategy<? super E>> getActivities() {
        return this.activities;
    }
}
