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

    private var minecraft: String? = null
    private var intermediate: IntermediateMappingsProvider? = null
    private var mappings: MappingsProvider? = null
    private val runs = project.objects.newInstance(RunConfigsImpl::class.java, project)

    override fun minecraft(version: String) {
        minecraft = version
    }

    override fun intermediate(mappings: IntermediateMappingsProvider) {
        intermediate = mappings
    }

    override fun mappings(mappings: MappingsProvider) {
        this.mappings = mappings
    }

    override fun getRuns(): RunConfigs = runs

}