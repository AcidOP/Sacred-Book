package codes.acidop.book.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class NetherKit {

    private final Player player;

    public NetherKit(Player player) {
        this.player = player;
    }

    public void get() {

        Material[] items = new Material[]{
                Material.BOW,
                Material.CROSSBOW,
                Material.ELYTRA,
                Material.ENCHANTED_GOLDEN_APPLE,
                Material.NETHERITE_AXE,
                Material.NETHERITE_HOE,
                Material.NETHERITE_PICKAXE,
                Material.NETHERITE_SHOVEL,
                Material.NETHERITE_SWORD,
                Material.TIPPED_ARROW
        };

        try {
            for(Material item: items) {

                if(item.name().equalsIgnoreCase("TIPPED_ARROW")) {
                    ItemStack harmingArrow = new ItemStack(item, 128);
                    PotionMeta potionMeta = (PotionMeta) harmingArrow.getItemMeta();
                    if (potionMeta == null) continue;
                    potionMeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
                    harmingArrow.setItemMeta(potionMeta);
                    player.getInventory().addItem(harmingArrow);
                    continue;
                }

                int maxStackSize = item.getMaxStackSize();

                ItemStack itemStack = new ItemStack(item, maxStackSize);
                player.getInventory().addItem(itemStack);
            }
        } catch (Exception e) {
            player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.RED + "Error: " + e.getMessage());
        }

        try {
            player.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            player.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            player.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        } catch (Exception e) {
            player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.RED + "Error: " + e.getMessage());
        }


//        Transfer offhand item to the inventory
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        int offHandItemAmount = offHandItem.getAmount();

//        Transfer offhand item to the inventory and add totems of undying
        if (offHandItemAmount > 0) {
            player.getInventory().addItem(offHandItem);
            player.getInventory().setItemInOffHand(null);
        }

        ItemStack totems = new ItemStack(Material.TOTEM_OF_UNDYING, 64);
        player.getInventory().setItemInOffHand(totems);

//        Enchant the newly added items
        try {
            new Enchant(player).enchant();
        } catch (Exception ignored) {}

        player.sendMessage(ChatColor.AQUA + "[Sacred Book]> " + ChatColor.GREEN + "Netherite Kit given");
    }
}
