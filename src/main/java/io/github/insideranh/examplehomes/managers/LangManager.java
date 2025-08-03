package io.github.insideranh.examplehomes.managers;

import io.github.insideranh.examplehomes.HomeExample;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class LangManager {

    private final HomeExample plugin = HomeExample.getInstance();
    private YamlConfiguration lang;

    public void load() {
        try {
            File langFile = new File(plugin.getDataFolder(), "lang.yml");
            this.lang = YamlConfiguration.loadConfiguration(langFile);

            if (!langFile.exists()) {
                Reader reader = new InputStreamReader(plugin.getResource("lang.yml"), StandardCharsets.UTF_8);
                YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(reader);

                this.lang.addDefaults(loadConfiguration);
                this.lang.options().copyDefaults(true);

                lang.save(langFile);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.lang.getString(path)));
    }

    public YamlConfiguration getLang() {
        return lang;
    }

}