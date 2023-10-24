package com.br.SuplaMent.utils.navigation.core;


import com.br.SuplaMent.utils.aEntity.IEntity;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Navigator<E extends IEntity> implements INavigator {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NavigatorContext context;
    @Autowired
    private Map<String, Navigation<E>> listNavigations = new HashMap();

    public Navigator() {
    }

    @PostConstruct
    public void init() {
        this.logger.info("==============================NAVIGATIONS==============================");
        Set<String> navigations = this.listNavigations.keySet();
        navigations.forEach(nav -> this.logger.info(nav));
        this.logger.info("=======================================================================");
    }

    public void run(IEntity aEntity, INavigationCase aCase) {
        aCase.setContext(this.context);
        this.navigate(aEntity, aCase);
    }

    private void navigate(IEntity aEntity, INavigationCase aCase) {
        if (aEntity != null) {
            Navigation<E> navigation = (Navigation)this.listNavigations.get(aCase.getName());
            if (navigation != null && !aCase.isSuspendExecution()) {
                List<IStrategy<? super E>> activities = navigation.getActivities();
                Iterator var5 = activities.iterator();

                while(var5.hasNext()) {
                    IStrategy strategy = (IStrategy)var5.next();
                    strategy.process(aEntity, aCase);
                    if (aCase.isSuspendExecution()) {
                        break;
                    }
                }
            } else if (!aCase.getName().contains("DEFAULT_CONTEXT")) {
                aCase.suspendExecution();
                aCase.getResult().setMessage(aCase.getName() + " - Não foi encontrada!");
                return;
            }
        } else {
            aCase.suspendExecution();
            aCase.getResult().setMessage("Entidade nula!");
        }

    }
}