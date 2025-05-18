package com.king_ultron99.hyperChat.utils

import com.google.inject.Inject
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.luckperms.api.LuckPermsProvider
import net.william278.papiproxybridge.api.PlaceholderAPI
import java.util.UUID

class TextReplacement @Inject constructor() {

    fun parseString(message: String, player: UUID): String? {
        return parseMiniMessageString(parsePlaceholders(parseHCPlaceholders(message, player).toString(), player).toString())
    }
    
    fun parsePlaceholders(message: String, player: UUID): String? {
        val papi = PlaceholderAPI.createInstance()
        val message = papi.formatComponentPlaceholders(message, player)
        return message.toString()
    }

    fun parseHCPlaceholders(message: String, player: UUID): String? {
        val luckPermsProvider = LuckPermsProvider.get()
        val luckPermsUser = luckPermsProvider.userManager.getUser(player)

        val prefix = luckPermsUser?.cachedData?.metaData?.prefix.toString()
        val suffix = luckPermsUser?.cachedData?.metaData?.suffix.toString()
        val nickname = luckPermsUser?.cachedData?.metaData?.getMetaValue("nickname").toString()

        return message.replace("{PREFIX}", prefix, false).replace("{SUFFIX}", suffix, false)
            .replace("{NICKNAME}", nickname, false)
    }

    fun parseMiniMessageString(message: String): String {
        return MiniMessage.miniMessage().deserialize(message).toString()
    }

    fun parseMiniMessageComponent(message: String): Component {
        return MiniMessage.miniMessage().deserialize(message)
    }

}