package jp.zaemongames.deathtp

import hazae41.minecraft.kutils.bukkit.init
import jp.zaemongames.deathtp.commands.DeathCommand
import jp.zaemongames.deathtp.configs.MainConfig
import jp.zaemongames.deathtp.listeners.PlayerDeath
import jp.zaemongames.deathtp.menus.TeleportConfirmMenu
import me.devnatan.inventoryframework.ViewFrame
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.RegisteredServiceProvider

class DeathTP : AbstractDeathTP()
{

    companion object
    {
        lateinit var econ: Economy
        lateinit var viewFrame: ViewFrame
    }

    override fun onEnable()
    {
        super.onEnable()

        MainConfig.autoSave = true
        init(MainConfig)

        if (!setupEconomy() ) {
            logger.severe(String.format("[%s] - Disabled due to no Vault dependency found!", this.name))
            server.pluginManager.disablePlugin(this)
            return
        }

        registerCommandsAndCompleters(
            Pair(Pair("death", DeathCommand()), DeathCommand())
        )

        registerListeners(
            PlayerDeath()
        )

        viewFrame = ViewFrame.create(this)
            .with(
                TeleportConfirmMenu()
            )
            .register()
    }

    private fun setupEconomy(): Boolean
    {
        if (server.pluginManager.getPlugin("Vault") == null)
        {
            return false
        }
        val rsp: RegisteredServiceProvider<Economy> = server.servicesManager.getRegistration(Economy::class.java)
            ?: return false
        econ = rsp.provider
        return true
    }
}