package xyz.spruceloader.roots

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.impl.RootsExtensionImpl
import xyz.spruceloader.roots.extension.impl.RunConfigsImpl
import java.nio.file.Files

class RootsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val ext = project.extensions.create("roots", RootsExtensionImpl::class.java)
        ext.runs {
            it.let { it as RunConfigsImpl }.apply {
                val folder = lazy {
                    val path = project.projectDir.toPath().resolve("runs")
                    if (!Files.isDirectory(path))
                        Files.createDirectories(path)

                    path
                }

                for (adapter in adapters) {
                    fun action() = runs.forEach { run -> adapter.write(project, run, folder) }

                    if (adapter.autoRun(project))
                        action()

                    project.tasks.create(adapter.getTaskName()) { task -> task.doLast { action() } }
                }
            }
        }
    }

}
