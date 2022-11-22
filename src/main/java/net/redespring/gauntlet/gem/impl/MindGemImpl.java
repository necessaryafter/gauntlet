package net.redespring.gauntlet.gem.impl;

import me.lucko.helper.item.ItemStackBuilder;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MindGemImpl implements Gem {


    @Override
    public String getName() {
        return "Mente";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(Material.INK_SACK)
                .name("§aJoia da " + getName())
                .lore(
                        "§7Esta jóia lhe concede o poder da",
                        "§fmineração rápida§7 por tempo ilimitado."
                )
                .data(4)
                .build();
    }

    @Override
    public PotionEffect getPotionEffect() {
        return PotionEffectType.FAST_DIGGING.createEffect(Integer.MAX_VALUE, 1);
    }

}
