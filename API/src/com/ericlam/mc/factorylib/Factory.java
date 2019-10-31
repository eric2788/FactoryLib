package com.ericlam.mc.factorylib;

@FunctionalInterface
public interface Factory<T> {
    T build();
}
