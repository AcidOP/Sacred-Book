package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Bed {

    private final Player player;
    private final Player target;

    public Bed(Player player, Player target) {
        this.player = player;
        this.target = target;
    }

    public void get() {

        if(target == null) {
            player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.RED + "Player not found");
            return;
        }

        Location bedLocation = target.getBedSpawnLocation();

        if(bedLocation == null) {
            player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.RED + "This bad boi has no bed");
            return;
        }

        int x = bedLocation.getBlockX();
        int y = bedLocation.getBlockY();
        int z = bedLocation.getBlockZ();

        player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.GREEN + "Bed location: " + ChatColor.GOLD + x + " " + y + " " + z);

    }
}
