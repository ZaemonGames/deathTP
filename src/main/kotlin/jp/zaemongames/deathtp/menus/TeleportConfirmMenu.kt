package jp.zaemongames.deathtp.menus

import jp.zaemongames.deathtp.actions.teleport
import jp.zaemongames.deathtp.configs.MainConfig
import jp.zaemongames.deathtp.managers.LocationManager
import jp.zaemongames.deathtp.managers.CountManager
import jp.zaemongames.deathtp.utils.translateName
import me.devnatan.inventoryframework.View
import me.devnatan.inventoryframework.ViewConfigBuilder
import me.devnatan.inventoryframework.context.RenderContext
import me.devnatan.inventoryframework.context.SlotClickContext
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.math.floor
import kotlin.math.pow

class TeleportConfirmMenu : View()
{
    override fun onInit(config: ViewConfigBuilder)
    {
        config.title("本当にテレポートしますか？")
        config.size(1)
        config.cancelOnClick()
    }

    override fun onFirstRender(render: RenderContext)
    {
        val player = render.player
        val deathLocation = LocationManager.get(player)!!
        val currentLocation = player.location

        val cost = roundDown((currentLocation.distance(deathLocation)), 1) * MainConfig.multiplier
        val count = CountManager.get(player)

        // cancelButton
        val cancelItem = ItemStack(Material.BARRIER)
        val cancelItemMeta = cancelItem.itemMeta
        cancelItemMeta.displayName(Component.text("キャンセル"))
        cancelItem.itemMeta = cancelItemMeta

        // detailIcon
        val detailItem = ItemStack(Material.PAPER)
        val detailItemMeta = detailItem.itemMeta
        detailItemMeta.displayName(Component.text("詳細"))
        val detailItemLore = listOf<Component>(
            Component.text("死んだワールド: ${deathLocation.world.translateName()}"),
            Component.text("死んだ座標: "),
            Component.text("  x: ${roundDown(deathLocation.x, 3)}"),
            Component.text("  y: ${roundDown(deathLocation.y, 3)}"),
            Component.text("  z: ${roundDown(deathLocation.z, 3)}"),
            Component.text("コスト: $cost")
        ).toMutableList()
        detailItemMeta.lore(detailItemLore)
        detailItem.itemMeta = detailItemMeta

        // confirmButton
        val confirmItem = ItemStack(Material.EMERALD_BLOCK)
        val confirmItemMeta = confirmItem.itemMeta
        confirmItemMeta.displayName(Component.text("テレポート"))
        confirmItem.itemMeta = confirmItemMeta

        render.slot(2, cancelItem)
            .onClick { click: SlotClickContext ->
                click.closeForPlayer()
            }
        render.slot(4, detailItem)
        render.slot(6, confirmItem)
            .onClick { click: SlotClickContext ->
                click.closeForPlayer()
                teleport(player, deathLocation, cost)
                CountManager.increase(player)
                if (count == MainConfig.limitPerLocation)
                {
                    LocationManager.unset(player)
                    CountManager.reset(player)
                }
            }
    }

    private fun roundDown(num: Double, digits: Int): Double
    {
        val d = 10.0.pow(digits.toDouble() - 1.0)
        return floor(num * d) / d
    }
}