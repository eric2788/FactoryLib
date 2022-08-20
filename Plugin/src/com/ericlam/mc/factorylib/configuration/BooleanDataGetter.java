package com.ericlam.mc.factorylib.configuration;

import java.util.Optional;

public final class BooleanDataGetter extends YamlBase implements DataGetter<Boolean> {

    public BooleanDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public Boolean getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getBoolean(path)).orElse(null);
    }

    @Override
    public Boolean getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getBoolean(path)).findAny().orElse(null);
    }
}
