package io.github.insideranh.examplehomes.managers;

import io.github.insideranh.examplehomes.HomeExample;
import io.github.insideranh.examplehomes.data.PlayerData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class DataManager {

    // HomeExample/players
    private final HomeExample plugin = HomeExample.getInstance();
    private final File dataDirectory = new File(plugin.getDataFolder(), "players");
    private final HashMap<UUID, PlayerData> players = new HashMap<>();

    public void load() {
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    public void loadPlayerData(Player player) {
        try {
            File playerFile = new File(dataDirectory, player.getUniqueId() + ".yml");
            if (!playerFile.exists()) {
                playerFile.createNewFile();
                this.players.put(player.getUniqueId(), new PlayerData(player.getUniqueId(), player.getName()));
            } else {
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(playerFile);
                this.players.put(player.getUniqueId(), new PlayerData(configuration));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void savePlayerData(Player player, boolean remove) {
        try {
            File playerFile = new File(dataDirectory, player.getUniqueId() + ".yml");
            if (!playerFile.exists()) {
                playerFile.createNewFile();
            }
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(playerFile);
            PlayerData playerData;
            if (remove) {
                playerData = this.players.remove(player.getUniqueId());
            } else {
                playerData = this.players.get(player.getUniqueId());
            }

            if (playerData != null) {
                playerData.save(configuration);
            }

            configuration.save(playerFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public PlayerData getPlayerData(UUID playerId) {
        return players.get(playerId);
    }

}