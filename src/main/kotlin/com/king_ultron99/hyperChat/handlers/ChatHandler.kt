package com.king_ultron99.hyperChat.handlers

import com.google.inject.Inject
import com.king_ultron99.hyperChat.Config.hcConfig
import com.velocitypowered.api.proxy.Player

class ChatHandler @Inject constructor(player: Player, msg: String) {
    private lateinit var reason: String

    init {
        if (hcConfig.formatter.enabled) {
            sendPlayerMessage(player, msg)
        }
    }

    private fun sendPlayerMessage(player: Player, msg: String) {
        // Check if the player is allowed to send messages, if not, provide a reason to the player
        when (!messageAllowed(player, msg)) {
            // Replace this with a component based message later.
            // as we aren't currently dealing with formatting of any kind yet, a plain message will suffice
            false -> player.sendPlainMessage(reason)
            else -> return // heh, you thought we'd actually handle the message? not yet :')
        }
    }

    private fun messageAllowed(player: Player, msg: String): (Boolean) {
        // always default to false if all checks don't pass
        // as we don't currently have any permission checks, we'll just allow everything
        return true
    }
}