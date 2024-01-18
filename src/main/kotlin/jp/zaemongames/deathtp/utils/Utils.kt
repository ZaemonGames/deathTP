package jp.zaemongames.deathtp.utils

import hazae41.minecraft.kutils.bukkit.msg
import hazae41.minecraft.kutils.textOf
import jp.zaemongames.deathtp.configs.MainConfig
import org.bukkit.World
import org.bukkit.entity.Player

fun Player.listMsg(list: List<String>)
{
    list.forEach {
        this.msg(textOf(it))
    }
}

fun Player.listMsg(list: List<String>, dic: HashMap<String, String>)
{
    list.forEach { msg->
        dic.forEach {
            this.msg(msg.replace(it.key, it.value))
        }
    }
}

fun World.translateName(): String
{
    val name = MainConfig.worldNameSection?.getString(this.name)
    return name ?: this.name
}