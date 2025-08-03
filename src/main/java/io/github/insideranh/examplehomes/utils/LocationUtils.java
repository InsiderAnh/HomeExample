package io.github.insideranh.examplehomes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtils {

    public static String parseLocationToString(Location location) {
        String worldName = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return worldName + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
    }

    public static Location parseLocationFromString(String location) {
        String[] parts = location.split(";", 6);
        if (parts.length < 6) {
            return null;
        }
        String worldName = parts[0];
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }

        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double z = Double.parseDouble(parts[3]);
        float yaw = Float.parseFloat(parts[4]);
        float pitch = Float.parseFloat(parts[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

}