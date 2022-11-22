package net.redespring.gauntlet.gem.impl;

import me.lucko.helper.item.ItemStackBuilder;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Material.REDSTONE;

public class PowerGemImpl implements Gem {

    @Override
    public String getName() {
        return "Poder";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(REDSTONE)
                .name("§aJoia do " + getName())
                .lore("§7Esta joia lhe concede o poder da", "§fresistência§7 por tempo ilimitado.")
                .build();
    }

    @Override
    public PotionEffect getPotionEffect() {
        return null;
    }
}
