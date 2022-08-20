package com.ericlam.mc.factorylib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class InventoryClickListener implements Listener {

    private static Map<Inventory, Map<Integer, Consumer<InventoryClickEvent>>> clickMap = new ConcurrentHashMap<>();

    public static void registerClick(Inventory inventory, Map<Integer, Consumer<InventoryClickEvent>> clicker) {
        clickMap.put(inventory, clicker.entrySet().stream().filter(map -> inventory.getItem(map.getKey()) != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        if (inventory == null || e.getSlotType() == InventoryType.SlotType.OUTSIDE || e.getCurrentItem() == null)
            return;
        Map<Integer, Consumer<InventoryClickEvent>> map = clickMap.get(inventory);
        if (map == null) return;
        e.setCancelled(true);
        Consumer<InventoryClickEvent> eventConsumer = map.get(e.getSlot());
        if (eventConsumer == null) return;
        eventConsumer.accept(e);
    }

}
