package net.redespring.gauntlet.registry;

import com.google.common.collect.Lists;
import net.redespring.gauntlet.gem.Gem;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

public class GemRegistry {

    private final List<Gem> registeredGems = Lists.newArrayList();

    public void registerGems(Gem... gems) {
        registeredGems.addAll(List.of(gems));
    }

    public Optional<Gem> getGemByItemStack(ItemStack itemStack) {
        return registeredGems.stream()
                .filter(gem -> gem.getItemStack().isSimilar(itemStack))
                .findFirst();
    }

}
