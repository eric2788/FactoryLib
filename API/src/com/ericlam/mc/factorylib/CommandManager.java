package com.ericlam.mc.factorylib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 指令管理器
 */
public interface CommandManager {

    /**
     * 註冊指令
     *
     * @param plugin  插件
     * @param command 指令類
     */
    void registerCommand(JavaPlugin plugin, Class<?> command);

}
