package xyz.spruceloader.roots.mappings.provider.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.impl.ProxyMapper
import xyz.spruceloader.roots.mappings.mapper.impl.TinyMapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import xyz.spruceloader.roots.mappings.provider.impl.intermediate.FabricIntermediary
import xyz.spruceloader.roots.util.extractTinyMappings
import java.net.URL

class YarnMappings(private val build: Int) : MappingsProvider {

    override fun provide(gameVersion: String, intermediateProvider: IntermediateMappingsProvider): Mapper {
        val native = intermediateProvider is FabricIntermediary

        val version = "$gameVersion%2Bbuild.$build"
        val classifier = if (native) "v2" else "mergedv2"
        val url =
            URL("https://maven.fabricmc.net/net/fabricmc/yarn/$version/yarn-$version-$classifier.jar")
        val mappings = url.openStream().use { extractTinyMappings(it) }

        // if the intermediate mappings are consistent with Fabric's own then we don't need to do anything
        if (native)
            return TinyMapper(mappings, "intermediary", "named")

        return ProxyMapper(
            intermediateProvider.provide(gameVersion).reversed(),
            TinyMapper(mappings, "official", "named")
        )
    }

}