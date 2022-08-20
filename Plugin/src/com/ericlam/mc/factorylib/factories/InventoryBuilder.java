package com.ericlam.mc.factorylib.factories;

import com.ericlam.mc.factorylib.InventoryClickListener;
import com.ericlam.mc.factorylib.InventoryFactory;
import com.google.inject.Inject;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class InventoryBuilder implements InventoryFactory {

    private String title;
    private int row;
    private Map<Integer, ItemStack> stackMap = new HashMap<>();
    private List<ItemStack> stacks = new ArrayList<>();
    private Map<Integer, Consumer<InventoryClickEvent>> clickMap = new HashMap<>();
    private ItemStack ringStack = null;
    private Map<Integer, ItemStack> rowFill = new HashMap<>();

    @Inject
    private Plugin plugin;

    @Override
    public InventoryFactory setTitle(@Nonnull String title) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        return this;
    }

    @Override
    public InventoryFactory setRow(int row) {
        this.row = Math.min(row, 6);
        return this;
    }


    @Override
    public InventoryFactory addItem(ItemStack... stack) {
        stacks.addAll(Arrays.asList(stack));
        return this;
    }

    @Override
    public InventoryFactory addItem(int slot, ItemStack stack) {
        stackMap.put(slot, stack);
        return this;
    }

    @Override
    public InventoryFactory click(int slot, Consumer<InventoryClickEvent> clicker) {
        clickMap.put(slot, clicker);
        return this;
    }

    @Override
    public InventoryFactory changePage(int slot, Supplier<Inventory> page) {
        return click(slot, e -> Bukkit.getScheduler().runTask(plugin, () -> e.getWhoClicked().openInventory(page.get())));
    }


    @Override
    public InventoryFactory row(ItemStack stack, int row) {
        this.rowFill.put(row, stack);
        return this;
    }

    @Override
    public InventoryFactory ring(ItemStack stack) {
        this.ringStack = stack;
        return this;
    }

    @Override
    public Inventory build() {
        Validate.notNull(title, "Title is null");
        Validate.isTrue(row > 0, "Row is zero");
        Inventory inventory = Bukkit.createInventory(null, row * 9, title);
        inventory.addItem(stacks.toArray(new ItemStack[0]));
        this.rowFill.forEach((row, stack) -> {
            for (int i = (row - 1) * ONE_ROW; i < row * ONE_ROW; i++) {
                inventory.setItem(i, stack);
            }
        });
        if (this.ringStack != null) {
            int row = 1;
            for (int i = (row - 1) * ONE_ROW; i < row * ONE_ROW; i++) {
                inventory.setItem(i, ringStack);
            }
            row = this.row;
            for (int i = (row - 1) * 9; i < row * 9; i++) {
                inventory.setItem(i, ringStack);
            }
            for (int j = 1; j < this.row; j++) {
                inventory.setItem((j - 1) * ONE_ROW, ringStack);
                inventory.setItem((j - 1) * ONE_ROW + END, ringStack);
            }
        }
        stackMap.forEach((inventory::setItem));
        InventoryClickListener.registerClick(inventory, clickMap);
        return inventory;
    }
}
