package com.king_ultron99.hyperChat.listeners

import com.google.inject.Inject
import com.king_ultron99.hyperChat.Config.hcConfig
import com.king_ultron99.hyperChat.handlers.ChatHandler
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.proxy.Player
import org.slf4j.Logger

class ChatListener @Inject constructor(val logger: Logger){

    @Subscribe(order = PostOrder.LAST)
    fun onPlayerChat(event: PlayerChatEvent) {
        val player: Player = event.player
        val message: String = event.message
        val currentServer = player.currentServer.get().serverInfo.name

        if (!event.result.isAllowed) {
            if (hcConfig.debug) { logger.debug("Message" + message + "was disallowed before HC could intervene") }
            return
        }
        // if the server is included in the list of blacklisted servers, or the server is null
        // we'll pull support, and allow the event to fall back to vanilla behaviour.
        if ((currentServer != null) && (currentServer in hcConfig.blacklistedServers)) {
            event.result = PlayerChatEvent.ChatResult.allowed()
        } else {
            // otherwise, WE get to fuck with their message :D
            ChatHandler(player, message)
        }

    }

}