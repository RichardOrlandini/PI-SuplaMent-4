package com.br.SuplaMent.utils.navigation.core;

import java.util.HashMap;
import java.util.Map;

public class DefaultAuthFactory implements IAuthFactory {
    public DefaultAuthFactory() {
    }
    public Map<String, Object> authContextParams() {
        return new HashMap();
    }
}