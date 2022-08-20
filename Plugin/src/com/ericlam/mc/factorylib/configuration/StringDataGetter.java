package com.ericlam.mc.factorylib.configuration;

import java.util.Objects;
import java.util.Optional;

public final class StringDataGetter extends YamlBase implements DataGetter<String> {

    public StringDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public String getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getString(path)).orElse(null);
    }

    @Override
    public String getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getString(path)).filter(Objects::nonNull).findAny().orElse(null);
    }
}
