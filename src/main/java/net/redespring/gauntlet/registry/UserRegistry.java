package net.redespring.gauntlet.registry;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.redespring.gauntlet.user.User;

import java.util.Map;
import java.util.Optional;

public class UserRegistry {

    private final Cache<String, User> cache = Caffeine.newBuilder().build();

    public void registerUser(User user) {
        cache.put(user.getPlayerName(), user);
    }

    public Optional<User> getUserByName(String name) {
        return Optional.ofNullable(cache.getIfPresent(name));
    }

    public void removeUser(User user) {
        cache.invalidate(user);
    }

}
