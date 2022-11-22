package net.redespring.gauntlet;

import co.aikar.commands.Locales;
import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.lucko.helper.plugin.ap.Plugin;
import me.lucko.helper.plugin.ap.PluginDependency;
import me.saiintbrisson.minecraft.ViewFrame;
import net.redespring.gauntlet.command.GauntletCommand;
import net.redespring.gauntlet.command.GemCommad;
import net.redespring.gauntlet.gem.Gem;
import net.redespring.gauntlet.inventory.UserGemsInventory;
import net.redespring.gauntlet.listener.PlayerConnectionListener;
import net.redespring.gauntlet.listener.PlayerInteractAtGemListener;
import net.redespring.gauntlet.registry.GemRegistry;
import net.redespring.gauntlet.registry.UserRegistry;
import org.bukkit.ChatColor;

import java.util.stream.Collectors;

@Plugin(
        name = "Gauntlet",
        version = "1.0.0",
        depends = {
                @PluginDependency("helper"),
                @PluginDependency("Factions"),
                @PluginDependency("InventoryFramework")
        },
        apiVersion = "1.8"
)
public final class GauntletPlugin extends ExtendedJavaPlugin {

    @Override
    protected void enable() {
        final UserRegistry userRegistry = new UserRegistry();
        final GemRegistry gemRegistry = new GemRegistry();

        bindModule(new PlayerConnectionListener(userRegistry));
        bindModule(new PlayerInteractAtGemListener(userRegistry, gemRegistry));

        final ViewFrame viewFrame = ViewFrame.of(this, new UserGemsInventory()).register();
        loadCommands(gemRegistry, userRegistry, viewFrame);
    }

    private void loadCommands(GemRegistry gemRegistry, UserRegistry userRegistry, ViewFrame viewFrame) {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.getCommandCompletions().registerCompletion("gems", context -> gemRegistry.getCollection()
                .stream()
                .map(Gem::getName)
                .collect(Collectors.toList()));

        commandManager.addSupportedLanguage(Locales.PORTUGUESE);
        commandManager.getLocales().setDefaultLocale(Locales.PORTUGUESE);

        commandManager.setFormat(MessageType.ERROR, ChatColor.RED);
        commandManager.setFormat(MessageType.SYNTAX, ChatColor.RED);
        commandManager.setFormat(MessageType.INFO, ChatColor.YELLOW);
        commandManager.setFormat(MessageType.HELP, ChatColor.YELLOW);

        commandManager.registerCommand(new GemCommad(gemRegistry));
        commandManager.registerCommand(new GauntletCommand(viewFrame, userRegistry));
    }
}
