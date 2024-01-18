package jp.zaemongames.deathtp.configs

import hazae41.minecraft.kutils.bukkit.PluginConfigFile

object MainConfig : PluginConfigFile("config")
{
    val multiplier by double("multiplier")
    val limitPerLocation by int("limitPerLocation")

    val noPermissionMessage by stringList("noPermissionMessage")
    val deathMessage by stringList("deathMessage")
    val deathLocationNotFoundMessage by stringList("deathLocationNotFoundMessage")
    val differentWorldMessage by stringList("differentWorldMessage")
    val teleportedMessage by stringList("teleportedMessage")
    val notEnoughBalanceMessage by stringList("notEnoughBalanceMessage")

    val worldNameSection by section("worldName")
}
