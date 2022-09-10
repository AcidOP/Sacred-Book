package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MaxEnchant {

    private final Player player;

    public MaxEnchant(Player player) {
        this.player = player;
    }

    //    Get all weapons in the player's inventory
    public void enchant() {
//        Get the player's inventory
        Inventory inventory = player.getInventory();
//        Array of enchantment in the game
        Enchantment[] enchantments = Enchantment.values();

//        Loop through all the enchantments and enchant every weapon in the player's inventory
//        with the enchantment if it can be applied.
        for (Enchantment enchantment : enchantments) {
            for (ItemStack itemStack : inventory) {
                if (itemStack == null) continue;
                try {
//                        I don't like Frost Walker
                    if (enchantment.equals(Enchantment.FROST_WALKER)) continue;
//                        Maximum Level of enchantment
                    int maxLevel = enchantment.getMaxLevel();
//                        I prefer Fortune 3 for my pickaxe
                    if ((itemStack.getType().name().contains("PICKAXE")) && (enchantment.equals(Enchantment.SILK_TOUCH))) {
                        continue;
                    }
//                        And Silk Touch for shovel
                    if ((itemStack.getType().name().contains("SHOVEL")) && (enchantment.equals(Enchantment.LOOT_BONUS_BLOCKS))) {
                        continue;
                    }
                    itemStack.addEnchantment(enchantment, maxLevel);
                } catch (Exception ignored) {
                }
            }
        }
        player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.GREEN + "All weapons in your inventory have been enchanted!");
    }
}
