package xyz.spruceloader.roots.extension.impl

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import xyz.spruceloader.roots.extension.RootsExtension
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider

open class RootsExtensionImpl(
    objectFactory: ObjectFactory,
    private val minecraft: Property<String> = objectFactory.property(String::class.java),
    private val intermediate: Property<IntermediateMappingsProvider> = objectFactory.property(
        IntermediateMappingsProvider::class.java
    ),
    private val mappings: Property<MappingsProvider> = objectFactory.property(MappingsProvider::class.java)
) : RootsExtension {

    override fun minecraft(): Property<String> = minecraft
    override fun minecraft(version: String) = minecraft.set(version)

    override fun intermediate(): Property<IntermediateMappingsProvider> = intermediate
    override fun intermediate(mappings: IntermediateMappingsProvider) = intermediate.set(mappings)

    override fun mappings(): Property<MappingsProvider> = mappings
    override fun mappings(mappings: MappingsProvider) = this.mappings.set(mappings)

}