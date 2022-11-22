package net.redespring.gauntlet.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.redespring.gauntlet.gem.Gem;
import net.redespring.gauntlet.gem.impl.SoulGemImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor @Getter
public class User {

    private final String playerName;
    private final Set<Gem> gems = Sets.newHashSetWithExpectedSize(6);

    public boolean addGem(Gem gem) {
        return gems.add(gem);
    }

    public void activeEffect(Gem gem) {
        final Player player = Bukkit.getPlayerExact(playerName);

        if(player.isOnline()) {
            if(gem instanceof SoulGemImpl) {
                for (PotionEffect potionEffect : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffect.getType());
                    player.addPotionEffect(potionEffect.getType().createEffect(Integer.MAX_VALUE, potionEffect.getAmplifier() + 1));
                }

                player.sendMessage("§aTodos seus efeitos foram aprimorados pela jóia da almaa.");
            }

            player.addPotionEffect(gem.getPotionEffect());
        }
    }

}
