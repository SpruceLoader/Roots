package xyz.spruceloader.roots.extension.impl

import xyz.spruceloader.roots.extension.RootsExtension
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider

open class RootsExtensionImpl : RootsExtension {

    private var minecraft: String? = null
    private var intermediate: IntermediateMappingsProvider? = null
    private var mappings: MappingsProvider? = null

    override fun minecraft(version: String) {
        minecraft = version
    }

    override fun intermediate(mappings: IntermediateMappingsProvider) {
        intermediate = mappings
    }

    override fun mappings(mappings: MappingsProvider) {
        this.mappings = mappings
    }

}