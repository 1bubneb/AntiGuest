package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.Prevention;
import de.cubeisland.AntiGuest.prevention.PreventionPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Prevents PVP
 *
 * @author Phillip Schichtel
 */
public class FightPrevention extends Prevention
{
    public FightPrevention(PreventionPlugin plugin)
    {
        super("fight", plugin);
        setEnableByDefault(true);
        setEnablePunishing(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(EntityDamageByEntityEvent event)
    {
        final Entity damager = event.getDamager();
        if (damager instanceof Player)
        {
            prevent(event, (Player)damager);
        }
        else if (damager instanceof Projectile)
        {
            final LivingEntity shooter = ((Projectile)damager).getShooter();
            if (shooter instanceof Player)
            {
                prevent(event, (Player)shooter);
            }
        }
    }
}