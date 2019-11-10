package com.ericlam.mc.factorylib.command;

/**
 * <p>返回類別，指令返回類別 | Return Type for Command Class</p>
 * <p>執行指令的方法必須返回此類。 | You must use this as your return type in your command class</p>
 */
public enum CommandResult {
    RETURN_TRUE,
    RETURN_FALSE,
    /**
     * 顯示幫助 | Show help menu
     */
    SHOW_HELP
}
