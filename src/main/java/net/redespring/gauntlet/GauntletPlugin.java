package net.redespring.gauntlet;

import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.lucko.helper.plugin.ap.Plugin;
import me.lucko.helper.plugin.ap.PluginDependency;
import net.redespring.gauntlet.listener.PlayerConnectionListener;
import net.redespring.gauntlet.listener.PlayerInteractAtGemListener;
import net.redespring.gauntlet.registry.GemRegistry;
import net.redespring.gauntlet.registry.UserRegistry;

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
    }
}
