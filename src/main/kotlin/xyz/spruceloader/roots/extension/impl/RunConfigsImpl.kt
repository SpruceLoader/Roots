package xyz.spruceloader.roots.extension.impl

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import xyz.spruceloader.roots.extension.RunConfigs
import xyz.spruceloader.roots.run.RunConfig
import xyz.spruceloader.roots.run.adapter.RunConfigAdapter
import xyz.spruceloader.roots.run.adapter.impl.EclipseAdapter
import xyz.spruceloader.roots.run.impl.RunConfigImpl
import javax.inject.Inject

open class RunConfigsImpl @Inject constructor(private val project: Project) : RunConfigs {

    val adapters = mutableListOf<RunConfigAdapter>()
    val runs = mutableListOf<RunConfig>()

    init {
        adapters.add(EclipseAdapter())
    }

    override fun create(name: String, creator: Action<RunConfig>) {
        val config = RunConfigImpl(project, name)
        creator.execute(config)
        runs.add(config)
    }

}