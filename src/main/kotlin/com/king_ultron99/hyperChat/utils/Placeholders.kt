package com.king_ultron99.hyperChat.utils

import com.google.inject.Inject
import net.william278.papiproxybridge.api.PlaceholderAPI
import java.util.UUID

class Placeholders @Inject constructor() {
    
    fun parsePlaceholders(message: String, player: UUID): String? {
        val papi = PlaceholderAPI.createInstance()
        val message = papi.formatComponentPlaceholders(message, player)
        return message.toString()
    }
    
}