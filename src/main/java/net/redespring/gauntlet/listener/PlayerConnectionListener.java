package net.redespring.gauntlet.listener;

import lombok.RequiredArgsConstructor;
import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import net.redespring.gauntlet.registry.UserRegistry;
import net.redespring.gauntlet.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerConnectionListener implements TerminableModule {

    private final UserRegistry userRegistry;

    @Override
    public void setup(@NotNull TerminableConsumer consumer) {
        Events.subscribe(PlayerJoinEvent.class)
                .handler(event -> {
                    final Player player = event.getPlayer();

                    User user = new User(player.getName()); // TODO: 22/11/2022 get user from databas

                    if(user == null)
                        user = new User(player.getName());

                    userRegistry.registerUser(user);
                }).bindWith(consumer);

        Events.subscribe(PlayerQuitEvent.class)
                .handler(event -> {
                    final Player player = event.getPlayer();
                    final Optional<User> userByName = userRegistry.getUserByName(player.getName());

                    if (userByName.isPresent()) {
                        // TODO: 22/11/2022 register in database
                        final User user = userByName.get();

                        System.out.printf("[%s] A user updated in database. (%s)", getClass().getName(), user);
                    }

                }).bindWith(consumer);
    }
}
