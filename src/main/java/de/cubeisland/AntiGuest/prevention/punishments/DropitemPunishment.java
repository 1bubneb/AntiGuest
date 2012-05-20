package de.cubeisland.AntiGuest.prevention.punishments;

import de.cubeisland.AntiGuest.prevention.Punishment;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Drops the player players held item
 *
 * @author Phillip Schichtel
 */
public class DropitemPunishment implements Punishment
{
    public String getName()
    {
        return "dropitem";
    }

    public void punish(Player player, ConfigurationSection config)
    {
        player.getWorld().dropItemNaturally(player.getLocation(), player.getItemInHand()).setPickupDelay(config.getInt("pickupDelay", 4) * 20);
        player.getInventory().clear(player.getInventory().getHeldItemSlot());
    }
}
