package com.ericlam.mc.factorylib.command;

/**
 * <p>返回類別，指令返回類別</p>
 * <p>執行指令的方法必須返回此類。</p>
 */
public enum CommandResult {
    RETURN_TRUE,
    RETURN_FALSE,
    /**
     * 顯示幫助
     */
    SHOW_HELP
}
