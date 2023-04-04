package xyz.spruceloader.roots.extension

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import xyz.spruceloader.roots.run.RunConfig

interface RootsExtension {

    fun minecraft(version: String)

    fun intermediate(mappings: IntermediateMappingsProvider)

    fun mappings(mappings: MappingsProvider)

    fun getRuns(): RunConfigs

    fun runs(action: Action<RunConfigs>) {
        action.execute(getRuns())
    }

}