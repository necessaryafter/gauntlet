package net.redespring.gauntlet.listener;

import lombok.RequiredArgsConstructor;
import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import net.redespring.gauntlet.gem.Gem;
import net.redespring.gauntlet.registry.GemRegistry;
import net.redespring.gauntlet.registry.UserRegistry;
import net.redespring.gauntlet.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerInteractAtGemListener implements TerminableModule {

    private final UserRegistry userRegistry;
    private final GemRegistry gemRegistry;

    @Override
    public void setup(@NotNull TerminableConsumer consumer) {
        Events.subscribe(PlayerInteractEvent.class)
                .filter(PlayerInteractEvent::hasItem)
                .filter(event -> event.getItem().getType() != Material.AIR)
                .handler(event -> {
                    final Player player = event.getPlayer();
                    final ItemStack itemStack = event.getItem();

                    final Optional<Gem> gemByItemStack = gemRegistry.getGemByItemStack(itemStack);
                    final Optional<User> userByName = userRegistry.getUserByName(player.getName());

                    if(gemByItemStack.isPresent() && userByName.isPresent()) {
                        final User user = userByName.get();
                        final Gem gem = gemByItemStack.get();
                        final boolean addedGem = user.addGem(gem);

                        if(!addedGem) {
                            player.sendMessage("§cVocê já possui esta jóia do infinito.");
                            return;
                        }

                        user.activeEffect(gem);
                        player.sendMessage("§aA joia foi ativada com sucesso!");

                        if(user.getGems().size() == 6) {
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                onlinePlayer.sendMessage(new String[] {
                                        "",
                                        String.format("§a§l MANOPLA!§a O jogador §f%s §acoletou todas as jóias do infinito.", onlinePlayer.getName()),
                                        ""
                                });
                            }
                        }
                    }
                }).bindWith(consumer);
    }
}
