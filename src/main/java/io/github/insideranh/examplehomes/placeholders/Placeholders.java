package io.github.insideranh.examplehomes.placeholders;

import io.github.insideranh.examplehomes.HomeExample;
import io.github.insideranh.examplehomes.data.PlayerData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {

    private final HomeExample plugin = HomeExample.getInstance();
    // %homes_count%

    @Override
    public @NotNull String getIdentifier() {
        return "homes";
    }

    @Override
    public @NotNull String getAuthor() {
        return "InsiderAnh";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(@NotNull Player player, @NotNull String placeholder) {
        PlayerData playerData = plugin.getDataManager().getPlayerData(player.getUniqueId());
        if (playerData == null) return "null";

        if (placeholder.equals("count")) {
            return String.valueOf(playerData.getHomes().size());
        }

        return "null";
    }

}
