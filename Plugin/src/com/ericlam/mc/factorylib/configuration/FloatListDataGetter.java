package com.ericlam.mc.factorylib.configuration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class FloatListDataGetter extends YamlBase implements DataGetter<List<Float>> {

    public FloatListDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public List<Float> getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getFloatList(path)).orElse(null);
    }

    @Override
    public List<Float> getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getFloatList(path)).filter(Objects::nonNull).findAny().orElse(null);
    }
}
