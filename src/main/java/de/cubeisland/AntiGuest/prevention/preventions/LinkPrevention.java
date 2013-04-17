package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.Prevention;
import de.cubeisland.AntiGuest.prevention.PreventionPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Pattern;

public class LinkPrevention extends Prevention
{
    private final Pattern linkRegex = Pattern.compile("(^|\\s)(https?://|www\\.)", Pattern.CASE_INSENSITIVE);

    public LinkPrevention(PreventionPlugin plugin)
    {
        super("link", plugin);
        setEnableByDefault(true);
        setEnablePunishing(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event)
    {
        if (this.linkRegex.matcher(event.getMessage()).find())
        {
            checkAndPrevent(event, event.getPlayer());
        }
    }
}
