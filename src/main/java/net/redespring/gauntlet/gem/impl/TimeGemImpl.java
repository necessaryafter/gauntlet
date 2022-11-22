package net.redespring.gauntlet.gem.impl;

import me.lucko.helper.item.ItemStackBuilder;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TimeGemImpl implements Gem {

    @Override
    public String getName() {
        return "Tempo";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(Material.EMERALD)
                .name("§aJoia do " + getName())
                .lore("§7Esta joia lhe concede o poder do", "§fsuper pulo§7 por tempo ilimitado.")
                .build();
    }

    @Override
    public PotionEffect getPotionEffect() {
        return PotionEffectType.JUMP.createEffect(Integer.MAX_VALUE, 1);
    }
}
