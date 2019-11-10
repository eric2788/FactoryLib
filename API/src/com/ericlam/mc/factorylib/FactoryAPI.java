package com.ericlam.mc.factorylib;

/**
 * 工廠 API | Factory API
 */
public interface FactoryAPI {

    /**
     * 獲取 背包工廠 | get InventoryFactory
     *
     * @return 背包工廠 | InventoryFactory
     */
    InventoryFactory getInventoryFactory();

    /**
     * 獲取 物品工廠 | get ItemStackFactory
     * @return 物品工廠 | ItemStackFactory
     */
    ItemStackFactory getItemStackFactory();


    /**
     * 獲取 指令管理器 | get Command Manager
     *
     * @return 指令管理器 | CommandManager
     */
    CommandManager getCommandManager();


}
