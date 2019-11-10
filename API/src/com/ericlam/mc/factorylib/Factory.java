package com.ericlam.mc.factorylib;

@FunctionalInterface
public interface Factory<T> {
    /**
     * 建造 | Build
     *
     * @return 相應類型 | Relevant Type
     */
    T build();
}
