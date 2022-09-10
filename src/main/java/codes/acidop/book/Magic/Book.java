package codes.acidop.book.Magic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import java.util.Objects;

public class Book implements Listener {

    static String[] availableCommands = new String[] {"teleport", "give", "location", "nkit", "enchant", "bed", "mode", "dupe"};

    @EventHandler
    public static void SignBookEvent(PlayerEditBookEvent event) {
        Player player = event.getPlayer();

//        Ignore if the title is not "cmd"
        if(!Objects.equals(event.getNewBookMeta().getTitle(), "cmd")) {
            player.sendMessage("Title is not cmd");
            return;
        }

//        Cancel the event for reuse of the book
        event.setCancelled(true);

//        Check if there is no content in the book
        if(event.getNewBookMeta().getPageCount() == 0) {
            player.sendMessage("You cannot write an empty book");
            return;
        }

//        First page of the book
        String pageContents = event.getNewBookMeta().getPage(1);


//        Change multiple spaces to one space
        pageContents = pageContents.replaceAll("\\s+", " ");

        String[] args = pageContents.split(" ");

        String command = args[0];

//        Check if the command is available
        boolean available = false;
        for(String cmd: availableCommands) {
            if(cmd.equals(command)) {
                available = true;
                break;
            }
        }

        if(!available) {
            player.sendMessage("Command not available");
            return;
        }

//        Execute the command
        if(command.equalsIgnoreCase("teleport")) new Teleport(player, args).teleport();
        if(command.equalsIgnoreCase("give")) new Give(player, args).give();
        if(command.equalsIgnoreCase("location")) new PlayerLocation(player, args).getLocation();
        if(command.equalsIgnoreCase("nkit")) new NetherKit(player).get();
        if(command.equalsIgnoreCase("enchant")) new MaxEnchant(player).enchant();
        if(command.equalsIgnoreCase("bed")) {
            Player target = player.getServer().getPlayer(args[1]);
            new Bed(player, target).get();
        }
        if(command.equalsIgnoreCase("mode")) new ChangeMode(player, args).changeMode();
        if(command.equalsIgnoreCase("dupe")) new Dupe(player).dupe();
    }
}