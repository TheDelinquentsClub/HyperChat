package com.king_ultron99.hyperChat

import com.king_ultron99.hyperChat.listeners.ChatListener

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import com.velocitypowered.api.plugin.annotation.DataDirectory
import java.nio.file.Path
import com.velocitypowered.api.plugin.Dependency

@Plugin(
    id = "hyperchat",
    name = "HyperChat",
    version = BuildConstants.VERSION,
    description = "Chat formatting built specifically for velocity, because fuck essx",
    dependencies = [
        Dependency("luckperms", false),
        Dependency("papiproxybridge", false),
    ]
)
class HyperChat @Inject constructor(val logger: Logger, val server: ProxyServer, @DataDirectory private val folder: Path) {

    init {
        Config.load(folder.resolve("config.yaml"))
        logger.info("HyperChat config loaded")
    }
    @Subscribe
    fun onInitialize(event: ProxyInitializeEvent) {
        server.eventManager.register(this, ChatListener(logger))
        logger.info("HyperChat Listeners initialised")
    }

}
