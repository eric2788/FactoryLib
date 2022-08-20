package com.ericlam.mc.factorylib.configuration;

import java.util.Optional;

public final class IntegerDataGetter extends YamlBase implements DataGetter<Integer> {


    public IntegerDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public Integer getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getInt(path)).orElse(null);
    }

    @Override
    public Integer getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getInt(path)).findAny().orElse(null);
    }
}
