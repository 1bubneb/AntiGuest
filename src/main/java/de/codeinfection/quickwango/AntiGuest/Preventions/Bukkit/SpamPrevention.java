package de.codeinfection.quickwango.AntiGuest.Preventions.Bukkit;

import de.codeinfection.quickwango.AntiGuest.AntiGuestBukkit;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import java.util.HashMap;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatEvent;

/**
 * Prevents spamming
 *
 * @author Phillip Schichtel
 */
public class SpamPrevention extends Prevention
{
    private int spamLockDuration;
    private HashMap<Player, Long> chatTimestamps;

    public SpamPrevention()
    {
        super("spam", AntiGuestBukkit.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.set("message", "&4Don't spam the chat!");
        config.set("lockDuration", 2);

        return config;
    }
    
    @Override
    public void enable(Server server, ConfigurationSection config)
    {
        super.enable(server, config);
        this.spamLockDuration = config.getInt("lockDuration") * 1000;
        this.chatTimestamps = new HashMap<Player, Long>();
    }

    @Override
    public void disable()
    {
        super.disable();
        this.chatTimestamps = null;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void handle(PlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        if (!can(player))
        {
            if (isChatLocked(player))
            {
                sendMessage(player);
                event.setCancelled(true);
            }
            else
            {
                setChatLock(player);
            }
        }
    }

    private void setChatLock(final Player player)
    {
        this.chatTimestamps.put(player, System.currentTimeMillis() + this.spamLockDuration);
    }
    
    private boolean isChatLocked(final Player player)
    {
        final Long nextPossible = this.chatTimestamps.get(player);
        if (nextPossible == null)
        {
            return false;
        }
        
        final long currentTime = System.currentTimeMillis();
        if (nextPossible < currentTime)
        {
            return false;
        }
        return true;
    }
}
