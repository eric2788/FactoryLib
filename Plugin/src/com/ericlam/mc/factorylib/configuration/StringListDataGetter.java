package com.ericlam.mc.factorylib.configuration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class StringListDataGetter extends YamlBase implements DataGetter<List<String>> {


    public StringListDataGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public List<String> getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getStringList(path)).orElse(null);
    }

    @Override
    public List<String> getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getStringList(path)).filter(Objects::nonNull).findAny().orElse(null);
    }
}
