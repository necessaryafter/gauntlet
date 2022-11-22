package net.redespring.gauntlet.command;

import co.aikar.commands.BaseCommand;

import co.aikar.commands.annotation.*;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.redespring.gauntlet.gem.Gem;
import net.redespring.gauntlet.registry.GemRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("jewel|givejoia")
@CommandPermission("gauntlet.gem.give")
@RequiredArgsConstructor
public class GemCommad extends BaseCommand {

    private final GemRegistry gemRegistry;

    @Default
    @CommandCompletion("@players @gems")
    @Syntax("<gem> <amount>")
    public void give(CommandSender commandSender, String playerName, String gemName, Integer amount) {
        final int finalAmount = amount == null ? 1 : amount;
        final Optional<Gem> gemOptional = gemRegistry.getGemByName(gemName);

        if(gemOptional.isEmpty()) {
            commandSender.sendMessage("§cEsta jóia do infinito não existe.");
            return;
        }

        final Gem gem = gemOptional.get();
        final Player player = Bukkit.getPlayerExact(playerName);

        if(player == null) {
            commandSender.sendMessage("§cJogador não encontrado.");
            return;
        }

        final ItemStack itemStack = gem.getItemStack();
        itemStack.setAmount(finalAmount);

        player.getInventory().addItem(itemStack);
        player.sendMessage(String.format(
                "§aVocê recebeu §f%sx jóias do %s.",
                finalAmount, gem.getName()
        ));
    }

}
