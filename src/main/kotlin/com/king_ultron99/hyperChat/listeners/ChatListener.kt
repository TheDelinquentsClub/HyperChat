package com.king_ultron99.hyperChat.listeners

import com.google.inject.Inject
import com.king_ultron99.hyperChat.Config.hcConfig
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.proxy.Player
import org.slf4j.Logger

class ChatListener @Inject constructor(val logger: Logger){

    @Subscribe
    fun onPlayerChat(event: PlayerChatEvent) {
        val player: Player = event.player
        val message: String = event.message
        val currentServer = player.currentServer.get().serverInfo.name

        if (!event.result.isAllowed) {
            if (hcConfig.debug) { logger.debug("Message" + message + "was disallowed before HC could intervene") }
            return
        }

        if ((currentServer != null) && (currentServer in hcConfig.blacklistedServers)) {
            event.result = PlayerChatEvent.ChatResult.allowed()
        } else {
            // send to connector
        }

    }

}