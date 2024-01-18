package jp.zaemongames.deathtp.listeners

import hazae41.minecraft.kutils.bukkit.msg
import jp.zaemongames.deathtp.configs.MainConfig
import jp.zaemongames.deathtp.managers.LocationManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeath : Listener
{
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent)
    {
        val player = event.player

        if (player.hasPermission("deathtp.teleport")) {
            MainConfig.deathMessage.forEach {
                player.msg(it)
            }
        }

        LocationManager.set(player, event.player.location)
    }
}