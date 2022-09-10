package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport{
    private final Player player;
    private final String[] args;

    private Player target;
    private double x, y, z;
    
    private String successMessage = ChatColor.AQUA + "[Sacred Book]> ";
    private String errorMessage = successMessage;
    private boolean error = false;

    public Teleport(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    private void setupArgs() {
        int length = args.length -1;

        switch (length) {
            case 1:
//                If a player provided to teleport to
                target = player.getServer().getPlayer(args[1]);
                
                if(target == null) {
                    errorMessage += ChatColor.RED + "Player not found";
                    error = true;
                    break;
                }
                break;
            case 2:
//                If only X and Z coordinate provided
                x = Double.parseDouble(args[1]);
                z = Double.parseDouble(args[2]);
//                Get the highest block location at y coordinate
                y = player.getWorld().getHighestBlockYAt((int) x, (int) z);
                break;
            case 3:
//                If all 3 coordinates provided
                x = Double.parseDouble(args[1]);
                y = Double.parseDouble(args[2]);
                z = Double.parseDouble(args[3]);
                break;
            default:
                errorMessage += ChatColor.RED + "Invalid arguments";
                error = true;
                break;
        }
    }

    public void teleport() {
        
        setupArgs();
        Location location = player.getLocation();

        if(error) {
            player.sendMessage(errorMessage);
            return;
        }
        
        if(target != null) {
            location = target.getLocation();
            successMessage += ChatColor.GREEN + "Teleported to " + target.getName();
        } else {

//            One block above the highest block
            y += 1;

            location.setX(x);
            location.setY(y);
            location.setZ(z);

            successMessage += ChatColor.GREEN + "Teleported to " + x + ", " + y + ", " + z;
        }
        
        try {
            player.teleport(location);
            player.sendMessage(successMessage);
        } catch (Exception e) {
            errorMessage += ChatColor.RED + "Teleportation failed due to: " + e.getMessage();
            player.sendMessage(errorMessage);
        }
    }
}