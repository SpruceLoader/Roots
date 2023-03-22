package xyz.spruceloader.roots.mappings.provider.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.impl.TinyMapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.util.extractTinyMappings
import java.net.URL

object QuiltHashed : IntermediateMappingsProvider {

    override fun provide(gameVersion: String): Mapper {
        val url =
            URL("https://maven.quiltmc.org/repository/release/org/quiltmc/hashed/$gameVersion/hashed-$gameVersion-v2.jar")
        val mappings = url.openStream().use { extractTinyMappings(it) }
        return TinyMapper(mappings, "official", "hashed");
    }

}