package com.br.SuplaMent.utils.navigation.core;

import java.util.Map;

public interface INavigatorContext {
    Map<String, Object> getAttributes();

    void setAttribute(String var1, Object var2);

    void setAttributes(Map<String, Object> var1);

    <R> R getAttribute(String var1);
}