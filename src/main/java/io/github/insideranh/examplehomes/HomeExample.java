package io.github.insideranh.examplehomes;

import io.github.insideranh.examplehomes.commands.DelHomeCMD;
import io.github.insideranh.examplehomes.commands.HomeCMD;
import io.github.insideranh.examplehomes.commands.SetHomeCMD;
import io.github.insideranh.examplehomes.listeners.PlayerListener;
import io.github.insideranh.examplehomes.managers.DataManager;
import io.github.insideranh.examplehomes.managers.LangManager;
import io.github.insideranh.examplehomes.placeholders.Placeholders;
import org.bukkit.plugin.java.JavaPlugin;

public class HomeExample extends JavaPlugin {

    private static HomeExample instance;
    private final DataManager dataManager;
    private final LangManager langManager;

    public HomeExample() {
        instance = this;

        this.dataManager = new DataManager();
        this.langManager = new LangManager();
    }

    @Override
    public void onEnable() {
        this.dataManager.load();
        this.langManager.load();

        getCommand("sethome").setExecutor(new SetHomeCMD());
        getCommand("home").setExecutor(new HomeCMD());
        getCommand("delhome").setExecutor(new DelHomeCMD());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholders().register();
        }
    }

    @Override
    public void onDisable() {

    }

    public static HomeExample getInstance() {
        return instance;
    }

    public LangManager getLangManager() {
        return langManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

}