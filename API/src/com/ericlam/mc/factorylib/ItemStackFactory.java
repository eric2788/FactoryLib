package com.ericlam.mc.factorylib;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public interface ItemStackFactory extends Factory<ItemStack> {

    ItemStackFactory material(@Nonnull Material material);

    ItemStackFactory name(@Nonnull String name);

    ItemStackFactory replaceLore(@Nonnull List<String> lore);

    ItemStackFactory lore(@Nonnull List<String> lore);

    ItemStackFactory lore(@Nonnull String... lore);

    ItemStackFactory enchant(Enchantment e, int level);

    ItemStackFactory durability(int dur);


}
