package com.ericlam.mc.factorylib;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 物品工廠 | ItemStack Factory
 */
public interface ItemStackFactory extends Factory<ItemStack> {

    /**
     * 設置類型 | set Material
     *
     * @param material 類型 | Material
     * @return this
     */
    ItemStackFactory material(@Nonnull Material material);

    /**
     * 設置名稱 | set Name
     * @param name 名稱 | name
     * @return this
     */
    ItemStackFactory name(@Nonnull String name);

    /**
     * 取代目前的 敘述 | replace lore
     * @param lore 敘述 | lore
     * @return this
     */
    ItemStackFactory replaceLore(@Nonnull List<String> lore);

    /**
     * 添加敘述 | add lore
     * @param lore 敘述 | lore
     * @return this
     */
    ItemStackFactory lore(@Nonnull List<String> lore);

    /**
     * 添加敘述 | add lore
     * @param lore 敘述 | lore
     * @return this
     */
    ItemStackFactory lore(@Nonnull String... lore);

    /**
     * 添加附魔 | add enchantment
     * @param e 附魔 | enchantment
     * @param level 等級 | level
     * @return his
     */
    ItemStackFactory enchant(Enchantment e, int level);

    /**
     * 設置耐久度 | set durability
     * @param dur 耐久度 | durability
     * @return this
     */
    ItemStackFactory durability(int dur);


}
