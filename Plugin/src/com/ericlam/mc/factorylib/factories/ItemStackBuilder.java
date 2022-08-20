package com.ericlam.mc.factorylib.factories;

import com.ericlam.mc.factorylib.ItemStackFactory;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

public final class ItemStackBuilder implements ItemStackFactory {

    private Material material;
    private String name;
    private List<String> lore = new ArrayList<>();
    private Map<Enchantment, Integer> enchantMap = new HashMap<>();
    private int durability = 0;

    @Override
    public ItemStackFactory material(@Nonnull Material material) {
        this.material = material;
        return this;
    }

    @Override
    public ItemStackFactory name(@Nonnull String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    @Override
    public ItemStackFactory replaceLore(@Nonnull List<String> lore) {
        this.lore = lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
        return this;
    }

    @Override
    public ItemStackFactory lore(@Nonnull List<String> lore) {
        this.lore.addAll(lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList()));
        return this;
    }


    @Override
    public ItemStackFactory lore(@Nonnull String... lore) {
        return lore(Arrays.asList(lore));
    }

    @Override
    public ItemStackFactory enchant(Enchantment e, int level) {
        this.enchantMap.put(e, level);
        return this;
    }

    @Override
    public ItemStackFactory durability(int dur) {
        this.durability = dur;
        return this;
    }

    @Override
    public ItemStack build() {
        Validate.notNull(name, "Item name is null");
        final ItemStack stack = new ItemStack(material);
        enchantMap.forEach(stack::addEnchantment);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        if (meta instanceof Damageable && durability > 0) {
            ((Damageable) meta).setDamage(durability);
        }
        stack.setItemMeta(meta);
        return stack;
    }
}
