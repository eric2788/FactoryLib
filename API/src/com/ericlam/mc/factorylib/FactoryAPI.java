package com.ericlam.mc.factorylib;

/**
 * 工廠 API
 */
public interface FactoryAPI {

    /**
     * 獲取 背包工廠
     *
     * @return 背包工廠
     */
    InventoryFactory getInventoryFactory();

    /**
     * 獲取 物品工廠
     * @return 物品工廠
     */
    ItemStackFactory getItemStackFactory();


}
