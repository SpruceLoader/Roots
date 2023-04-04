package xyz.spruceloader.roots.run.adapter

import org.gradle.api.Project
import java.nio.file.Path
import xyz.spruceloader.roots.run.RunConfig

interface RunConfigAdapter {
	
	fun write(project: Project, config: RunConfig, folder: Lazy<Path>)

	fun getTaskName(): String
	fun autoRun(project: Project): Boolean

}