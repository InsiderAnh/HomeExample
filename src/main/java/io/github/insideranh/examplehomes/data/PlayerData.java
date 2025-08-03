package io.github.insideranh.examplehomes.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private UUID playerId;
    private String playerName;
    private HashMap<String, HomeData> homes = new HashMap<>();

    private long nextCanTeleport = 0L;

    public PlayerData(UUID playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public PlayerData(YamlConfiguration config) {
        this.playerId = UUID.fromString(config.getString("playerId"));
        this.playerName = config.getString("playerName");

        for (String key : config.getConfigurationSection("homes").getKeys(false)) {
            String homeName = config.getString("homes." + key + ".name");
            String homeLocation = config.getString("homes." + key + ".location");

            this.homes.put(homeName, new HomeData(homeName, homeLocation));
        }
    }

    public void save(YamlConfiguration config) {
        config.set("playerId", playerId.toString());
        config.set("playerName", playerName);
        config.set("homes", null);

        for (Map.Entry<String, HomeData> entry : homes.entrySet()) {
            String homeName = entry.getKey();
            HomeData homeData = entry.getValue();

            config.set("homes." + homeName + ".name", homeData.getName());
            config.set("homes." + homeName + ".location", homeData.getLocation());
        }
    }

    public void setNextCanTeleport(long nextCanTeleport) {
        this.nextCanTeleport = nextCanTeleport;
    }

    public long getNextCanTeleport() {
        return nextCanTeleport;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public HashMap<String, HomeData> getHomes() {
        return homes;
    }
}