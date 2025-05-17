package com.king_ultron99.hyperChat

import kotlinx.serialization.*
import java.nio.file.Files
import com.charleskorn.kaml.Yaml
import java.nio.file.Path

@Serializable
data class PluginConfig (
    val blacklistedServers: Array<String> = arrayOf("jail_server", "server_i_do_not_like"),
    val debug: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PluginConfig

        return blacklistedServers.contentEquals(other.blacklistedServers)
    }

    override fun hashCode(): Int {
        return blacklistedServers.contentHashCode()
    }
}

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