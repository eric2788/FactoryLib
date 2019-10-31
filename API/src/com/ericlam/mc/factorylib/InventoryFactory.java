package com.ericlam.mc.factorylib;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 背包工廠
 */
public interface InventoryFactory extends Factory<Inventory> {

    /**
     * 第一行的 位置
     */
    int ONE_ROW = 9;

    /**
     * 中央 位置
     */
    int CENTER = 4;

    /**
     * 初始位置
     */
    int START = 0;

    /**
     * 結尾位置
     */
    int END = 8;


    /**
     * 設置界面標題
     *
     * @param title 標題
     * @return this
     */
    InventoryFactory setTitle(@Nonnull String title);

    /**
     * 設置行數
     *
     * @param row 行數
     * @return this
     */
    InventoryFactory setRow(int row);

    /**
     * 添加物品
     *
     * @param stack 物品
     * @return this
     */
    InventoryFactory addItem(ItemStack... stack);

    /**
     * 添加物品
     * @param slot 位置
     * @param stack 物品
     * @return this
     */
    InventoryFactory addItem(int slot, ItemStack stack);

    /**
     * 添加點擊事件
     * @param slot 點擊位置
     * @param clicker 事件
     * @return this
     */
    InventoryFactory click(int slot, Consumer<InventoryClickEvent> clicker);

    /**
     * 轉換頁面
     *
     * @param slot 位置
     * @param page 界面
     * @return this
     */
    InventoryFactory changePage(int slot, Supplier<Inventory> page);

    /**
     * 填滿一行
     *
     * @param stack 物品
     * @param row 行數
     * @return this
     */
    InventoryFactory row(ItemStack stack, int row);

    /**
     * 環狀圍繞
     * @param stack 物品
     * @return this
     */
    InventoryFactory ring(ItemStack stack);

}
