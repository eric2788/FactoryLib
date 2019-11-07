package com.ericlam.mc.factorylib;

import org.bukkit.plugin.java.JavaPlugin;

public interface CommandManager {

    void registerCommand(JavaPlugin plugin, Class<?> command);

}
