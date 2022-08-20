package com.ericlam.mc.factorylib.configuration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class DoubleListDataGetter extends YamlBase implements DataGetter<List<Double>> {

    public DoubleListDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public List<Double> getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getDoubleList(path)).orElse(null);
    }

    @Override
    public List<Double> getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getDoubleList(path)).filter(Objects::nonNull).findAny().orElse(null);
    }
}
