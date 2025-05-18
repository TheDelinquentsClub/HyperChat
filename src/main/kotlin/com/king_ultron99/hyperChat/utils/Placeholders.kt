package com.king_ultron99.hyperChat.utils

import com.google.inject.Inject
import net.luckperms.api.LuckPermsProvider
import net.william278.papiproxybridge.api.PlaceholderAPI
import java.util.UUID

class Placeholders @Inject constructor() {
    
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

        return message.replace("{PREFIX}", prefix, false).replace("{SUFFIX}", suffix, false).replace("{NICKNAME}", nickname, false)
    }
    
}