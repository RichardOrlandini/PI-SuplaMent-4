package com.br.SuplaMent.utils.aEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationEntity {
    public ApplicationEntity() {
    }
    public Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}

