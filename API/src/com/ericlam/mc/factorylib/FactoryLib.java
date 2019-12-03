package com.ericlam.mc.factorylib;

import com.ericlam.mc.factorylib.configuration.ConfigManager;
import org.bukkit.plugin.Plugin;

public final class FactoryLib implements FactoryAPI {

    public static FactoryAPI getApi() {
        return null;
    }

    @Override
    public InventoryFactory getInventoryFactory() {
        return null;
    }

    @Override
    public ItemStackFactory getItemStackFactory() {
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        return null;
    }

    @Override
    public ConfigManager getConfigManager(Plugin plugin) {
        return null;
    }
}
