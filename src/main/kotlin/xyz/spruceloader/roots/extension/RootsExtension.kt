package xyz.spruceloader.roots.extension

import org.gradle.api.Action
import org.gradle.api.artifacts.Dependency
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider

interface RootsExtension {

    fun minecraft(version: String): Dependency

    fun intermediate(mappings: IntermediateMappingsProvider)

    fun mappings(mappings: MappingsProvider)

    fun getRuns(): RunConfigs

    fun runs(action: Action<RunConfigs>) {
        action.execute(getRuns())
    }

}