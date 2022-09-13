package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ChangeMode {

    private final Player player;
    private final String[] args;

    GameMode mode;
    boolean error = false;

    public ChangeMode(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    private void setupArgs() {
        int length = args.length -1;

        if(length != 1) {
            error = true;
            return;
        }

        String modeName = args[1].toUpperCase();

        switch (modeName) {
            case "SURVIVAL":
                mode = GameMode.SURVIVAL;
                break;
            case "CREATIVE":
                mode = GameMode.CREATIVE;
                break;
            case "ADVENTURE":
                mode = GameMode.ADVENTURE;
                break;
            case "SPECTATOR":
                mode = GameMode.SPECTATOR;
                break;
            default:
                error = true;
                break;
        }
    }

    public void changeMode() {
        setupArgs();
        String heading = ChatColor.AQUA + "[Sacred Book]> ";

        if(error) {
            String errorMessage = heading + ChatColor.RED + "Invalid arguments";
            player.sendMessage(errorMessage);
            return;
        }
        try {
            player.setGameMode(mode);
            String successMessage = heading + ChatColor.GREEN + "Game mode changed to " + mode.name();
            player.sendMessage(successMessage);
        } catch (Exception e) {
            String errorMessage = heading + ChatColor.RED + "The following error occurred: " + e.getMessage();
            player.sendMessage(errorMessage);
        }
    }
}
