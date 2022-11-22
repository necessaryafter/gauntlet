package net.redespring.gauntlet.gem;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public interface Gem {

    String getName();
    ItemStack getItemStack();
    PotionEffect getPotionEffect();

}
