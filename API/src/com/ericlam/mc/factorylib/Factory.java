package com.ericlam.mc.factorylib;

@FunctionalInterface
public interface Factory<T> {
    /**
     * 建造
     *
     * @return 相應類型
     */
    T build();
}
