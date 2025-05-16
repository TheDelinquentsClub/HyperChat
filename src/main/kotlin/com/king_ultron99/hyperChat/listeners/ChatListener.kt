package com.king_ultron99.hyperChat.listeners

import com.google.inject.Inject
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.proxy.Player
import org.slf4j.Logger

class ChatListener @Inject constructor(private val logger: Logger){

    @Subscribe
    fun onPlayerChat(event: PlayerChatEvent) {
        val player: Player = event.player
        val message: String = event.message

        logger.info("[Chat] ${player.username}: $message")

        if (!event.result.isAllowed) {
            return
        } else if (event.player.currentServer is in config.servers.blacklist) {
            event.result = PlayerChatEvent.ChatResult.allowed()
        } else {
            // send to connector
        }

    }

}