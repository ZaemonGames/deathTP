package jp.zaemongames.deathtp.managers

import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

object LocationManager
{
    private val deathLocations: HashMap<UUID, Location> = HashMap()

    fun set(player: Player, location: Location)
    {
        val uuid = player.uniqueId
        deathLocations[uuid] = location
        CountManager.reset(player)
    }

    fun unset(player: Player)
    {
        val uuid = player.uniqueId
        deathLocations.remove(uuid)
    }

    fun get(player: Player): Location?
    {
        val uuid = player.uniqueId
        return deathLocations[uuid]
    }
}