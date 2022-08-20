package com.ericlam.mc.factorylib.configuration;

import com.ericlam.mc.factorylib.configuration.list.DoubleList;
import com.ericlam.mc.factorylib.configuration.list.FloatList;
import com.ericlam.mc.factorylib.configuration.list.StringList;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class YamlManager implements ConfigManager {

    final Map<String, FileConfiguration> configDataMap = new ConcurrentHashMap<>();
    final Map<String, FileConfiguration> msgDataMap = new ConcurrentHashMap<>();
    private final Map<Class<?>, DataGetter<?>> dataGetterMap = new HashMap<>();
    private final Plugin plugin;

    public YamlManager(Plugin plugin) {
        this.plugin = plugin;
        this.dataGetterMap.put(String.class, new StringDataGetter(this));
        this.dataGetterMap.put(Double.class, new DoubleListDataGetter(this));
        this.dataGetterMap.put(Boolean.class, new BooleanDataGetter(this));
        this.dataGetterMap.put(Integer.class, new IntegerDataGetter(this));
        this.dataGetterMap.put(StringList.class, new StringListDataGetter(this));
        this.dataGetterMap.put(DoubleList.class, new DoubleListDataGetter(this));
        this.dataGetterMap.put(FloatList.class, new FloatListDataGetter(this));
        this.dataGetterMap.put(ItemStack.class, new ItemStackGetter(this));
    }

    public Map<String, FileConfiguration> getAllConfigMap() {
        Map<String, FileConfiguration> map = new HashMap<>();
        map.putAll(configDataMap);
        map.putAll(msgDataMap);
        return map;
    }

    private void register(String config, boolean msg) {
        String yml = config + ".yml";
        File file = new File(plugin.getDataFolder(), yml);
        if (!file.exists()) plugin.saveResource(yml, true);
        if (msg) {
            msgDataMap.put(config, YamlConfiguration.loadConfiguration(file));
        } else {
            configDataMap.put(config, YamlConfiguration.loadConfiguration(file));
        }
    }

    @Override
    public void registerConfig(String... config) {
        for (String s : config) {
            register(s, false);
        }
    }

    @Override
    public void registerMessage(String... msgConfig) {
        for (String s : msgConfig) {
            register(s, true);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> DataGetter<T> getDataGetter(Class<T> type) {
        if (dataGetterMap.containsKey(type)) {
            return (DataGetter<T>) dataGetterMap.get(type);
        } else {
            return new CustomDataGetter<>(o -> {
                if (type.isEnum()) {
                    return Arrays.stream(type.getEnumConstants()).filter(s -> s.toString().equalsIgnoreCase(o.toString())).findAny().orElse(null);
                } else {
                    return type.cast(o);
                }
            }, this);
        }
    }

    @Override
    public <T> void registerTypeGetter(Class<T> type, Function<Object, T> getter) {
        this.dataGetterMap.put(type, new CustomDataGetter<>(getter, this));
    }

    @Override
    public String getMessage(String path) {
        return msgDataMap.values().stream().filter(s -> s.contains(path)).map(s -> s.getString(path)).filter(Objects::nonNull).map(s -> ChatColor.translateAlternateColorCodes('&', s)).findAny().orElse(null);
    }

    @Override
    public List<String> getMessageList(String path) {
        return msgDataMap.values().stream().filter(s -> s.contains(path)).map(s -> s.getStringList(path)).filter(l -> !l.isEmpty()).map(s -> s.stream().map(a -> ChatColor.translateAlternateColorCodes('&', a)).collect(Collectors.toList())).findAny().orElse(null);
    }

    @Override
    public FileConfiguration getConfig(String config) {
        return getAllConfigMap().get(config);
    }
}
