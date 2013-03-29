package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.*;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

/**
 * Prevents changing the hotbar of the player
 */
public class HotbarPrevention extends Prevention
{
    public HotbarPrevention(PreventionPlugin plugin)
    {
        super("hotbar", plugin);
        setEnablePunishing(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onSlotChange(PlayerItemHeldEvent event)
    {
        checkAndPrevent(event, event.getPlayer());
    }
}
