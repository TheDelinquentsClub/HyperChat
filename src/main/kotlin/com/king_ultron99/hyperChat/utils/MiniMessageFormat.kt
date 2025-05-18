package com.king_ultron99.hyperChat.utils

import com.google.inject.Inject
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

class MiniMessageFormat @Inject constructor() {

    fun parseMiniMessageString(message: String): String {
        return MiniMessage.miniMessage().deserialize(message).toString()
    }

    fun parseMiniMessageComponent(message: String): Component {
        return MiniMessage.miniMessage().deserialize(message)
    }

}