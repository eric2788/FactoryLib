package com.ericlam.mc.factorylib;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 物品工廠
 */
public interface ItemStackFactory extends Factory<ItemStack> {

    /**
     * 設置類型
     *
     * @param material 類型
     * @return this
     */
    ItemStackFactory material(@Nonnull Material material);

    /**
     * 設置名稱
     * @param name 名稱
     * @return this
     */
    ItemStackFactory name(@Nonnull String name);

    /**
     * 取代目前的 敘述
     * @param lore 敘述
     * @return this
     */
    ItemStackFactory replaceLore(@Nonnull List<String> lore);

    /**
     * 添加敘述
     * @param lore 敘述
     * @return this
     */
    ItemStackFactory lore(@Nonnull List<String> lore);

    /**
     * 添加敘述
     * @param lore 敘述
     * @return this
     */
    ItemStackFactory lore(@Nonnull String... lore);

    /**
     * 添加附魔
     * @param e 附魔
     * @param level 等級
     * @return his
     */
    ItemStackFactory enchant(Enchantment e, int level);

    /**
     * 設置耐久度
     * @param dur 耐久度
     * @return this
     */
    ItemStackFactory durability(int dur);


}
