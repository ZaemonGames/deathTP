package jp.zaemongames.deathtp.actions

import jp.zaemongames.deathtp.DeathTP
import jp.zaemongames.deathtp.configs.MainConfig
import jp.zaemongames.deathtp.utils.listMsg
import org.bukkit.Location
import org.bukkit.entity.Player

val econ = DeathTP.econ

fun teleport(player: Player, location: Location)
{
    player.teleport(location)
    player.listMsg(MainConfig.teleportedMessage)
}

fun teleport(player: Player,location: Location, cost: Double)
{
    if (!player.hasPermission("deathtp.free"))
    if (!econ.has(player, cost))
    {
        player.listMsg(MainConfig.notEnoughBalanceMessage)
        return
    }

    econ.withdrawPlayer(player, cost)

    teleport(player, location)
}
