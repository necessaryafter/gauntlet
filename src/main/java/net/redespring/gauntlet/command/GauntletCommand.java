package net.redespring.gauntlet.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.ViewFrame;
import net.redespring.gauntlet.inventory.UserGemsInventory;
import net.redespring.gauntlet.registry.UserRegistry;
import net.redespring.gauntlet.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

@CommandAlias("manopla")
@RequiredArgsConstructor
public class GauntletCommand extends BaseCommand {

    private final ViewFrame viewFrame;
    private final UserRegistry userRegistry;

    @Default
    public void openInventory(CommandSender commandSender) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cExclusivo à jogadores.");
            return;
        }

        final Player player = (Player) commandSender;
        final Optional<User> userOptional = userRegistry.getUserByName(player.getName());

        if(userOptional.isEmpty()) {
            player.sendMessage("§cNão foi possível carregar a sua conta no servidor...");
            return;
        }

        viewFrame.open(UserGemsInventory.class, player, Map.of(
                "user_data", userOptional.get()
        ));
    }
}
