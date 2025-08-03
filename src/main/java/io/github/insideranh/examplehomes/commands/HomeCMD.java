package io.github.insideranh.examplehomes.commands;

import io.github.insideranh.examplehomes.HomeExample;
import io.github.insideranh.examplehomes.data.HomeData;
import io.github.insideranh.examplehomes.data.PlayerData;
import io.github.insideranh.examplehomes.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeCMD implements TabExecutor {

    private final HomeExample plugin = HomeExample.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        // /home <casaNombre>
        if (args.length < 1) {
            player.sendMessage("§cNo has especificado el nombre de la casa. §e/home <nombre>");
            return true;
        }

        String casaNombre = args[0];
        PlayerData playerData = plugin.getDataManager().getPlayerData(player.getUniqueId());
        if (playerData == null) return true;

        if (!playerData.getHomes().containsKey(casaNombre)) {
            player.sendMessage("§cNo existe esa casa.");
            return true;
        }

        HomeData homeData = playerData.getHomes().get(casaNombre);
        Location location = LocationUtils.parseLocationFromString(homeData.getLocation());
        if (location == null) {
            player.sendMessage("§cNo se pudo encontrar la casa.");
            return true;
        }

        if (playerData.getNextCanTeleport() > System.currentTimeMillis()) {
            player.sendMessage("§cDebes esperar un momento mas para teletransportarte.");
            return true;
        }
        playerData.setNextCanTeleport(System.currentTimeMillis() + 1000L);

        player.teleport(location);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return new ArrayList<>();
        Player player = (Player) sender;
        PlayerData playerData = plugin.getDataManager().getPlayerData(player.getUniqueId());
        if (playerData == null) return new ArrayList<>();

        /*
        String lastArg = args[args.length - 1];
        ArrayList<String> homes = new ArrayList<>();
        for (String homeName : playerData.getHomes().keySet()) {
            if (homeName.startsWith(lastArg)) {
                homes.add(homeName);
            }
        }
        return homes;
        */
        return playerData.getHomes().keySet().stream().filter(s -> s.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }

}
