package de.cubeisland.AntiGuest.Preventions.Bukkit;

import de.cubeisland.AntiGuest.AntiGuestBukkit;
import de.cubeisland.AntiGuest.Prevention;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerBedEnterEvent;

/**
 * Prevents bed usage
 *
 * @author Phillip Schichtel
 */
public class BedPrevention extends Prevention
{
    public BedPrevention()
    {
        super("bed", AntiGuestBukkit.getInstance());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(PlayerBedEnterEvent event)
    {
        prevent(event, event.getPlayer());
    }
}