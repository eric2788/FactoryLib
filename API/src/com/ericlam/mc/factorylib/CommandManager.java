package com.ericlam.mc.factorylib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 指令管理器 | Command Manager
 */
public interface CommandManager {

    /**
     * 註冊指令 | register Command
     *
     * @param plugin  插件 | plugin
     * @param command 指令類 | command class
     */
    void registerCommand(JavaPlugin plugin, Class<?> command);

}
