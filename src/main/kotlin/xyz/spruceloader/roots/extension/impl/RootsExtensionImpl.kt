package xyz.spruceloader.roots.extension.impl

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.internal.instantiation.InstantiatorFactory
import org.gradle.internal.service.ServiceLookup
import xyz.spruceloader.roots.extension.RootsExtension
import xyz.spruceloader.roots.extension.RunConfigs
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import javax.inject.Inject

open class RootsExtensionImpl(project: Project): RootsExtension {

    private val minecraft: Property<String> = project.objects.property(String::class.java)
    private val intermediate: Property<IntermediateMappingsProvider> = project.objects.property(
        IntermediateMappingsProvider::class.java
    )
    private val mappings: Property<MappingsProvider> = project.objects.property(MappingsProvider::class.java)
    private val runs = project.objects.newInstance(RunConfigsImpl::class.java, project)

    override fun minecraft(): Property<String> = minecraft
    override fun intermediate(): Property<IntermediateMappingsProvider> = intermediate
    override fun mappings(): Property<MappingsProvider> = mappings
    override fun runs(): RunConfigs = runs

}