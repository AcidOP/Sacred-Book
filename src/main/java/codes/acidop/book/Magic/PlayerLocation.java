package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerLocation {

    private final Player player;
    private final String[] args;
    private Player target;

    private String successMessage = ChatColor.AQUA + "[Sacred Book]> ";
    private String errorMessage = successMessage;
    private boolean error = false;

    public PlayerLocation(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    private void setupArgs() {

        String playerName = args[1];
        try {
            target = player.getServer().getPlayer(playerName);
        } catch (Exception e) {
            target = null;
            errorMessage += ChatColor.RED + "Player not found";
            error = true;
        }
    }

    public void getLocation() {

        setupArgs();

        if(error) {
            player.sendMessage(errorMessage);
            return;
        }

        String targetName = target.getName();
        Location targetLocation = target.getLocation();

        int x = targetLocation.getBlockX();
        int y = targetLocation.getBlockY();
        int z = targetLocation.getBlockZ();

        successMessage += ChatColor.GREEN + targetName + " is at: " + ChatColor.GOLD + x + " " + y + " " + z;
        player.sendMessage(successMessage);
    }
}
