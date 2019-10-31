package com.ericlam.mc.factorylib;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface InventoryFactory extends Factory<Inventory> {

    int ONE_ROW = 9;
    int CENTER = 4;
    int START = 0;
    int END = 8;


    InventoryFactory setTitle(@Nonnull String title);

    InventoryFactory setRow(int row);

    InventoryFactory addItem(ItemStack... stack);

    InventoryFactory addItem(int slot, ItemStack stack);

    InventoryFactory click(int slot, Consumer<InventoryClickEvent> clicker);

    InventoryFactory changePage(int slot, Supplier<Inventory> page);

    InventoryFactory row(ItemStack stack, int row);

    InventoryFactory ring(ItemStack stack);

}
