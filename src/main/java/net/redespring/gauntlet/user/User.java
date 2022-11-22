package net.redespring.gauntlet.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.redespring.gauntlet.gem.Gem;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor @Getter
public class User {

    private final String playerName;
    private final Set<Gem> gems = Sets.newHashSetWithExpectedSize(6);

    public boolean addGem(Gem gem) {
        return gems.add(gem);
    }

}
