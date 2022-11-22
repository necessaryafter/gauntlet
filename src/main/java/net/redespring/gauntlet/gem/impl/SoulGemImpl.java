package net.redespring.gauntlet.gem.impl;

import me.lucko.helper.item.ItemStackBuilder;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SoulGemImpl implements Gem {

    @Override
    public String getName() {
        return "Alma";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of();
    }

    @Override
    public PotionEffect getPotionEffect() {
        return null;
    }
}
