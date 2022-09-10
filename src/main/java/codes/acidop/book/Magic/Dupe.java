package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Dupe {
    private final Player player;

    public Dupe(Player player) {
        this.player = player;
    }

    public void dupe() {

        ItemStack offHand = player.getInventory().getItemInOffHand();

        if(offHand.toString().equals("AIR")) {
            String err = ChatColor.AQUA + "[Sacred Book]> " + ChatColor.RED + "You must hold an item in your off hand";
            player.sendMessage(err);
            return;
        }

        Inventory inventory = player.getInventory();

        inventory.addItem(offHand);

    }
}
