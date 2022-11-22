package net.redespring.gauntlet.gem;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface Gem {

    String getName();
    ItemStack getItemStack();
    PotionEffect getPotionEffect();

}
