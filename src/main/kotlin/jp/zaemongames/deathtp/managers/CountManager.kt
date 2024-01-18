package jp.zaemongames.deathtp.managers

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

object CountManager
{
    private val usageCount: HashMap<UUID, Int> = HashMap()

    fun reset(player: Player)
    {
        usageCount[player.uniqueId] = 0
    }

    fun increase(player: Player)
    {
        val uuid = player.uniqueId
        usageCount[uuid] = usageCount[uuid]?.plus(1) ?: 1
    }

    fun get(player: Player): Int
    {
        return usageCount[player.uniqueId] ?: 0
    }
}