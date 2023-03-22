package xyz.spruceloader.roots.mappings.provider.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.impl.TinyMapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.util.extractTinyMappings
import java.net.URL

class FabricIntermediate(val build: Int) : IntermediateMappingsProvider {

    override fun provide(gameVersion: String): Mapper {
        val url =
            URL("https://maven.fabricmc.net/net/fabricmc/intermediary/$gameVersion/intermediary-$gameVersion-v2.jar")
        val mappings = url.openStream().use { extractTinyMappings(it) }
        return TinyMapper(mappings, "official", "intermediary");
    }

}