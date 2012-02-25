package de.codeinfection.quickwango.AntiGuest.Preventions;

import de.codeinfection.quickwango.AntiGuest.AntiGuest;
import de.codeinfection.quickwango.AntiGuest.FilteredItemPrevention;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 *
 * @author Phillip
 */
public class PickupPrevention extends FilteredItemPrevention
{
    public PickupPrevention()
    {
        super("pickup", AntiGuest.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.set("message", "&4You are not allowed to pickup items!");
        config.set("messageDelay", 3);

        return config;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(PlayerPickupItemEvent event)
    {
        preventThrottled(event, event.getPlayer(), event.getItem().getItemStack().getType());
    }
}