package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Give {

    private final Player player;
    private final String[] args;

    private int quantity;
    private Material material;

    private String successMessage = ChatColor.AQUA + "[Sacred Book]> ";
    private String errorMessage = successMessage;
    private boolean error = false;

    public Give(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    private void setupArgs() {
        int length = args.length - 1;

        if(length <= 0 || length > 2) {
            errorMessage += ChatColor.RED + "Invalid arguments";
            error = true;
            return;
        }

        String materialName = args[1].toUpperCase();
        material = Material.getMaterial(materialName);

        if(material == null) {
            errorMessage += ChatColor.RED + "The item was not found";
            error = true;
            return;
        }

//        No quantity provided. Default to the maximum stack size.
        if(length == 1) {
            quantity = material.getMaxStackSize();
            return;
        }

        try {
            quantity = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            errorMessage += ChatColor.RED + "The quantity must be a number";
            error = true;
        }
    }

    public void give() {
        setupArgs();

        if(error) {
            player.sendMessage(errorMessage);
            return;
        }

        ItemStack itemStack = new ItemStack(material, quantity);

        try {
            player.getInventory().addItem(itemStack);
            successMessage += ChatColor.GREEN + "Given " + quantity + " x " + material.name();
            player.sendMessage(successMessage);
        }
        catch (Exception e) {
            errorMessage += ChatColor.RED + "The following error occurred: " + e.getMessage();
            player.sendMessage(errorMessage);
        }
    }
}