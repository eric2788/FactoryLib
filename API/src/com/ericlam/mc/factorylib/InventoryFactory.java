package com.ericlam.mc.factorylib;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 背包工廠 | InventoryFactory
 */
public interface InventoryFactory extends Factory<Inventory> {

    /**
     * 第一行的 位置 | line one slot
     */
    int ONE_ROW = 9;

    /**
     * 中央 位置 | center slot
     */
    int CENTER = 4;

    /**
     * 初始位置 | start slot
     */
    int START = 0;

    /**
     * 結尾位置 | end slot
     */
    int END = 8;


    /**
     * 設置界面標題 | set inventory title
     *
     * @param title 標題 | title
     * @return this
     */
    InventoryFactory setTitle(@Nonnull String title);

    /**
     * 設置行數 | set row
     *
     * @param row 行數 | row
     * @return this
     */
    InventoryFactory setRow(int row);

    /**
     * 添加物品 | add item
     *
     * @param stack 物品 | ItemStack
     * @return this
     */
    InventoryFactory addItem(ItemStack... stack);

    /**
     * 添加物品 | add item
     * @param slot 位置 | slot
     * @param stack 物品 | ItemStack
     * @return this
     */
    InventoryFactory addItem(int slot, ItemStack stack);

    /**
     * 添加點擊事件 | add click event
     * @param slot 點擊位置 | Click slot
     * @param clicker 事件 | Event
     * @return this
     */
    InventoryFactory click(int slot, Consumer<InventoryClickEvent> clicker);

    /**
     * 轉換頁面 | change Page
     *
     * @param slot 位置 | slot
     * @param page 界面 | Inventory
     * @return this
     */
    InventoryFactory changePage(int slot, Supplier<Inventory> page);

    /**
     * 填滿一行 | fill one row
     *
     * @param stack 物品 | ItemStack
     * @param row 行數 | line
     * @return this
     */
    InventoryFactory row(ItemStack stack, int row);

    /**
     * 環狀圍繞 | item encircle
     * @param stack 物品 | ItemStack
     * @return this
     */
    InventoryFactory ring(ItemStack stack);

}
