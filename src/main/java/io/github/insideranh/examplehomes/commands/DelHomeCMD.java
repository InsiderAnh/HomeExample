package io.github.insideranh.examplehomes.commands;

import io.github.insideranh.examplehomes.HomeExample;
import io.github.insideranh.examplehomes.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCMD implements CommandExecutor {

    private final HomeExample plugin = HomeExample.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        PlayerData playerData = plugin.getDataManager().getPlayerData(player.getUniqueId());
        if (playerData == null) return true;

        if (args.length < 1) {
            player.sendMessage("§cNo has especificado el nombre de la casa. §e/delhome <nombre>");
            return true;
        }

        String casaNombre = args[0];
        if (!playerData.getHomes().containsKey(casaNombre)) {
            player.sendMessage("§cNo existe esa casa.");
            return true;
        }

        playerData.getHomes().remove(casaNombre);
        plugin.getDataManager().savePlayerData(player, false);

        player.sendMessage("§aHas eliminado la casa §e" + casaNombre + "§a.");
        return false;
    }

}