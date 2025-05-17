package com.king_ultron99.hyperChat

import kotlinx.serialization.*
import java.nio.file.Files
import com.charleskorn.kaml.Yaml
import java.nio.file.Path

@Serializable
data class PluginConfig (
    val blacklistedServers: List<String> = listOf("jail_server", "server_i_do_not_like"),
    val formatter: ChatFormatter,
    val debug: Boolean = false,
)

@Serializable
data class ChatFormatter (
    val enabled: Boolean = true,
    val servers: List<String> = listOf("hub", "chat_server"),
)

object Config {
    lateinit var hcConfig: PluginConfig
        private set

    fun load(path: Path) {
        if (!Files.exists(path)) {
            Files.createDirectories(path.parent)
            val content = Yaml.default.encodeToString(PluginConfig.serializer())
            Files.writeString(path, content)
        }

        val content = Files.readString(path)
        hcConfig = Yaml.default.decodeFromString(content)
    }
}