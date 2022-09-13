package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;


public class Help {

    private final Player player;
    private final String[] args;

    public Help(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    public static String[] commands = {
            "[*] COMMANDS:",
            "1 teleport",
            "2 give",
            "3 location",
            "4 kit",
            "5 enchant",
            "6 bed",
            "7 mode",
            "8 dupe",
            "9 help"
    };

    public static String[] usage = {
            "[*] USAGE:",
            "1. Write the command in the first page of the book",
            "2. Set the title \"cmd\"",
            "3. Sign the book",
    };

    public void helpCommand(String askedCommand) {

        String helpMessage = ChatColor.AQUA + "[Sacred Book]> " + ChatColor.GREEN +
                "Help for command " + ChatColor.RED + askedCommand + ":\n" +
                ChatColor.GOLD;

        switch (askedCommand) {
            case "teleport":
                helpMessage += "Syntax: teleport [player] or coords\n" +
                        "Teleport to a player or to coordinates\n" +
                        "Example: teleport AcidOP // Only player\n" +
                        "Example: teleport 420 69 // Without specifying y \n" +
                        "Example: teleport 100 100 100 // All 3 axis";
                break;

            case "give":
                helpMessage += "Syntax: give [item] [amount]\n" +
                        "Give an item to a player\n" +
                        "Example: give diamond 64\n" +
                        "Example: give diamond_block //Maximum stack size\n" +
                        "Items: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html";
                break;

            case "location":
                helpMessage += "Syntax: location [player]\n" +
                        "Get the location of a player\n" +
                        "Example: location AcidOP";
                break;

            case "bed":
                helpMessage += "Syntax: bed [player]\n" +
                        "Coords of a player's bed\n" +
                        "Example: bed AcidOP";
                break;

            case "mode":
                helpMessage += "Syntax: mode [mode]\n" +
                        "MODES: survival, creative, adventure, spectator\n" +
                        "Example: mode creative";
                break;

            case "kit":
                helpMessage += "Syntax: kit\nGives an overpowered kit";
                break;

            case "enchant":
                helpMessage += "Syntax: enchant\nEnchants everything in your inventory with the best enchantments";
                break;

            case "dupe":
                helpMessage += "Syntax: dupe\nDuplicates offhand item";
                break;

            case "help":
                helpMessage += "Syntax: help\nShows this message\nYou must be really really retarded to need help with this command";
                break;
            default:
                helpMessage += "Command not found";
                break;
        }

        player.sendMessage(helpMessage);

    }

    public void help() {

        int length = args.length - 1;

        if(length != 0) {
            String askedCommand = args[1].toLowerCase();
            helpCommand(askedCommand);
            return;
        }

        String cmd = String.join("\n", commands);
        String use = String.join("\n", usage);

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        assert bookMeta != null;
        bookMeta.setTitle("Sacred Book");
        bookMeta.setAuthor("AcidOP");

        bookMeta.addPage(cmd);
        bookMeta.addPage(use);

        book.setItemMeta(bookMeta);

        player.getInventory().addItem(book);
        player.sendMessage("[hint] help [command]");
    }
}

