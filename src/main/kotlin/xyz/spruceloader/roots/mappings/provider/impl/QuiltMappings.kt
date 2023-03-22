package xyz.spruceloader.roots.mappings.provider.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.impl.ProxyMapper
import xyz.spruceloader.roots.mappings.mapper.impl.TinyMapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import xyz.spruceloader.roots.mappings.provider.impl.intermediate.QuiltHashed
import xyz.spruceloader.roots.util.extractTinyMappings
import java.net.URL

class QuiltMappings(private val build: Int) : MappingsProvider {

    override fun provide(gameVersion: String, intermediateProvider: IntermediateMappingsProvider): Mapper {
        val native = intermediateProvider is QuiltHashed

        val version = "$gameVersion%2Bbuild.$build"
        val classifier = if (native) "v2" else "mergedv2"
        val url =
            URL("https://maven.quiltmc.org/repository/release/org/quiltmc/quilt-mappings/$version/quilt-mappings-$version-$classifier.jar")
        val mappings = url.openStream().use { extractTinyMappings(it) }

        // if the intermediate mappings are consistent with Quilt's hashed then we don't need to do anything
        if (native)
            return TinyMapper(mappings, "hashed", "named")

        return ProxyMapper(
            intermediateProvider.provide(gameVersion).reversed(),
            TinyMapper(mappings, "official", "named")
        )
    }

}