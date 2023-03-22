package xyz.spruceloader.roots.extension

import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider

interface RootsExtension {

    fun minecraft(version: String)

    fun intermediate(mappings: IntermediateMappingsProvider)
    fun mappings(mappings: MappingsProvider)

}