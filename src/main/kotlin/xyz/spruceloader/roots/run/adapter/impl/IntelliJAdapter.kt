package xyz.spruceloader.roots.run.adapter.impl

import org.gradle.api.Project
import xyz.spruceloader.roots.run.RunConfig
import xyz.spruceloader.roots.run.adapter.RunConfigAdapter

object IntelliJAdapter : RunConfigAdapter {

    override fun write(project: Project, config: RunConfig) {
        TODO("Not yet implemented")
    }

    override fun getTaskName(): String = "genIntelliJRuns"

    override fun autoRun(project: Project): Boolean = System.getProperty("idea.version") != null

}