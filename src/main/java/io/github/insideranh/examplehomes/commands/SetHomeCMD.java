package io.github.insideranh.examplehomes.commands;

import io.github.insideranh.examplehomes.HomeExample;
import io.github.insideranh.examplehomes.data.HomeData;
import io.github.insideranh.examplehomes.data.PlayerData;
import io.github.insideranh.examplehomes.utils.LocationUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCMD implements CommandExecutor {

    private final HomeExample plugin = HomeExample.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        // /sethome <casaNombre>
        if (args.length < 1) {
            player.sendMessage("§cNo has especificado el nombre de la casa. §e/sethome <nombre>");
            return true;
        }

        // args = 1
        String casaNombre = args[0];
        PlayerData playerData = plugin.getDataManager().getPlayerData(player.getUniqueId());
        if (playerData == null) return true;

        if (playerData.getHomes().containsKey(casaNombre)) {
            player.sendMessage("§cEsta casa ya existe.");
            return true;
        }

        playerData.getHomes().put(casaNombre, new HomeData(casaNombre, LocationUtils.parseLocationToString(player.getLocation())));
        plugin.getDataManager().savePlayerData(player, false);

        player.sendMessage(plugin.getLangManager().getString("messages.setHome").replace("<casaNombre>", casaNombre));
        return false;
    }

}