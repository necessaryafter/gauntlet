package net.redespring.gauntlet.inventory;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import net.redespring.gauntlet.gem.Gem;
import net.redespring.gauntlet.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class UserGemsInventory extends View {

    public UserGemsInventory() {
        super(4, "Manopla do Infinito");
    }

    @Override
    protected void onRender(@NotNull ViewContext context) {
        final User user = context.get("user_data");
        final Set<Gem> gemSet = user.getGems();

        // TODO: 22/11/2022 render inventory
    }
}
