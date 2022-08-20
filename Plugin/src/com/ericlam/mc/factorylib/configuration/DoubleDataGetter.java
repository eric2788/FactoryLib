package com.ericlam.mc.factorylib.configuration;

import java.util.Optional;

public final class DoubleDataGetter extends YamlBase implements DataGetter<Double> {

    public DoubleDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public Double getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getDouble(path)).orElse(null);
    }

    @Override
    public Double getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getDouble(path)).findAny().orElse(null);
    }
}
