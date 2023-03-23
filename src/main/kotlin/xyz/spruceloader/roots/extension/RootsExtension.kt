package xyz.spruceloader.roots.extension

import org.gradle.api.provider.Property
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider

interface RootsExtension {

    fun minecraft(): Property<String>
    fun minecraft(version: String)

    fun intermediate(): Property<IntermediateMappingsProvider>
    fun intermediate(mappings: IntermediateMappingsProvider)

    fun mappings(): Property<MappingsProvider>
    fun mappings(mappings: MappingsProvider)

}