package com.ericlam.mc.factorylib.configuration;

import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

public final class ItemStackGetter extends YamlBase implements DataGetter<ItemStack> {

    public ItemStackGetter(YamlManager yamlManager) {
        super(yamlManager);
    }

    @Override
    public ItemStack getData(String config, String path) {
        return Optional.ofNullable(yamlManager.configDataMap.get(config)).map(d -> d.getItemStack(path)).orElse(null);
    }

    @Override
    public ItemStack getData(String path) {
        return yamlManager.configDataMap.values().stream().filter(f -> f.contains(path)).map(f -> f.getItemStack(path)).filter(Objects::nonNull).findAny().orElse(null);
    }
}
