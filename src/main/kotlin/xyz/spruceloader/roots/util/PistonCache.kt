package xyz.spruceloader.roots.util

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.util.stream.StreamSupport

object PistonCache {

    private val URL = URL("https://piston-meta.mojang.com/mc/game/version_manifest.json")
    private val CACHE = mutableMapOf<String, JsonObject>()

    private val manifest by lazy { URL.openStream().use { JsonParser.parseReader(InputStreamReader(it)).asJsonObject } }

    fun getVersion(version: String): JsonObject {
        return CACHE.computeIfAbsent(version) {
            URL(
                StreamSupport.stream(manifest["versions"].asJsonArray.spliterator(), false)
                    .map(JsonElement::getAsJsonObject)
                    .filter { it["id"].asString == version }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException("$version is not a valid Minecraft version :/") }["url"].asString
            ).openStream().use {
                JsonParser.parseReader(InputStreamReader(it)).asJsonObject
            }
        }
    }

}