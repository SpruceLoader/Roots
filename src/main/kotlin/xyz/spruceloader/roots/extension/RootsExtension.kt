package xyz.spruceloader.roots.extension

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import xyz.spruceloader.roots.run.RunConfig

interface RootsExtension {

    fun minecraft(): Property<String>
    fun minecraft(version: String) = minecraft().set(version)

    fun intermediate(): Property<IntermediateMappingsProvider>
    fun intermediate(mappings: IntermediateMappingsProvider) = intermediate().set(mappings)

    fun mappings(): Property<MappingsProvider>		
    fun mappings(mappings: MappingsProvider) = mappings().set(mappings)

    fun runs(): RunConfigs
    fun runs(action: Action<RunConfigs>) {
        action.execute(runs())
    }

}