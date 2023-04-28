package xyz.spruceloader.roots

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.RootsExtension
import xyz.spruceloader.roots.extension.impl.RootsExtensionImpl
import xyz.spruceloader.roots.extension.impl.RunConfigsImpl
import java.nio.file.Files

class RootsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val ext = project.extensions.create(RootsExtension::class.java, "roots", RootsExtensionImpl::class.java) as RootsExtensionImpl
        val runs = ext.getRuns()
        val folder = lazy {
            val path = project.projectDir.toPath().resolve("runs")
            if (!Files.isDirectory(path))
                Files.createDirectories(path)

            path
        }

        for (adapter in runs.adapters) {
            fun action() = runs.runs.forEach { adapter.write(project, it, folder) }

            if (adapter.autoRun(project))
                action()

            project.tasks.create(adapter.getTaskName()) { it.doLast { action() } }
        }
    }

}
