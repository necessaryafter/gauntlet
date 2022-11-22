package net.redespring.gauntlet.gem.impl;

import me.lucko.helper.item.ItemStackBuilder;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RealityGemImpl implements Gem {

    @Override
    public String getName() {
        return "Realidade";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(Material.INK_SACK)
                .data(DyeColor.RED.getData())
                .name("§aJoia do " + getName())
                .lore("§7Esta joia lhe concede o poder da", "§fforça§7 por tempo ilimitado.")
                .build();
    }

    @Override
    public PotionEffect getPotionEffect() {
        return PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1);
    }
}
