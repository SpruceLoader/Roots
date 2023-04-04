package xyz.spruceloader.roots.run.adapter

import org.gradle.api.Project
import java.nio.file.Path
import xyz.spruceloader.roots.run.RunConfig

interface RunConfigAdapter {
	
	fun write(project: Project, config: RunConfig)

	fun getTaskName(): String
	fun autoRun(project: Project): Boolean

}