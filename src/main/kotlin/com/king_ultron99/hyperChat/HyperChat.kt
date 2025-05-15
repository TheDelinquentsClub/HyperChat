package com.king_ultron99.hyperChat;

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger

@Plugin(id = "hyperchat", name = "HyperChat", version = BuildConstants.VERSION, description = "Chatformatting built specifically for velocity, because fuck essx")
class HyperChat @Inject constructor(val logger: Logger) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
    }
}
