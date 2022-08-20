package com.ericlam.mc.factorylib;

import com.ericlam.mc.factorylib.configuration.ConfigManager;
import com.ericlam.mc.factorylib.configuration.YamlManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class FactoryLib extends JavaPlugin implements FactoryAPI {

    private static FactoryAPI api;
    private final Map<Plugin, ConfigManager> configManagerMap = new HashMap<>();
    private ModuleImplementor moduleImplementor = new ModuleImplementor();
    private Injector injector;
    private CommandManager commandManager;

    public static FactoryAPI getApi() {
        return api;
    }

    @Override
    public void onLoad() {
        api = this;
        moduleImplementor.bind(Plugin.class, this);
        injector = Guice.createInjector(moduleImplementor);
        commandManager = injector.getInstance(CommandManager.class);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getLogger().info("FactoryLib Activated by Hydranapse_");
    }

    @Override
    public InventoryFactory getInventoryFactory() {
        return injector.getInstance(InventoryFactory.class);
    }

    @Override
    public ItemStackFactory getItemStackFactory() {
        return injector.getInstance(ItemStackFactory.class);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public ConfigManager getConfigManager(Plugin plugin) {
        this.configManagerMap.putIfAbsent(plugin, new YamlManager(plugin));
        return configManagerMap.get(plugin);
    }


}
