package com.ericlam.mc.factorylib.configuration;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class CustomDataGetter<T> extends YamlBase implements DataGetter<T> {

    private final Function<Object, T> getter;

    public CustomDataGetter(Function<Object, T> getter, YamlManager yamlManager) {
        super(yamlManager);
        this.getter = getter;
    }

    @Override
    public T getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.get(path)).map(getter).orElse(null);
    }

    @Override
    public T getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.get(path)).filter(Objects::nonNull).map(getter).findAny().orElse(null);
    }
}
