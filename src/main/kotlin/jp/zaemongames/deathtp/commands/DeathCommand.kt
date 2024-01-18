package jp.zaemongames.deathtp.commands

import com.google.common.collect.ImmutableMap
import hazae41.minecraft.kutils.bukkit.msg
import jp.zaemongames.deathtp.DeathTP
import jp.zaemongames.deathtp.configs.MainConfig
import jp.zaemongames.deathtp.managers.LocationManager
import jp.zaemongames.deathtp.menus.TeleportConfirmMenu
import jp.zaemongames.deathtp.utils.listMsg
import jp.zaemongames.deathtp.utils.translateName
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class DeathCommand : CommandExecutor, TabCompleter
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean
    {
        if (sender !is Player)
        {
            sender.msg("Only player can execute this command!")
            return true
        }

        if (!sender.hasPermission("deathtp.teleport"))
        {
            sender.listMsg(MainConfig.noPermissionMessage)
            return true
        }

        val deathLocation = LocationManager.get(sender)
        if (deathLocation == null)
        {
            sender.listMsg(MainConfig.deathLocationNotFoundMessage)
            return true
        }

        if (deathLocation.world != sender.location.world)
        {
            sender.listMsg(MainConfig.differentWorldMessage, hashMapOf(
                "%world" to deathLocation.world.translateName()
            ))
            return true
        }

        DeathTP.viewFrame.open(TeleportConfirmMenu::class.java, sender, ImmutableMap.of(
            "deathLocation", deathLocation,
            "currentLocation", sender.location
        ))

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): List<String>
    {
        return emptyList()
    }
}