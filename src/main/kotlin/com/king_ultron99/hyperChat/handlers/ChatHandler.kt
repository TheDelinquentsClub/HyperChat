package com.king_ultron99.hyperChat.handlers

import com.google.inject.Inject
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.king_ultron99.hyperChat.Config.hcConfig

class ChatHandler @Inject constructor(val event: PlayerChatEvent) {
    init {
        if (hcConfig.formatter.enabled) {
            formatMessage(event)
        }
    }

    fun formatMessage(event: PlayerChatEvent) {

    }

}