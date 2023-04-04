package xyz.spruceloader.roots

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.impl.RootsExtensionImpl
import xyz.spruceloader.roots.extension.impl.RunConfigsImpl

class RootsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val ext = project.extensions.create("roots", RootsExtensionImpl::class.java)
        ext.runs().let { it as RunConfigsImpl }.apply {
            for (adapter in adapters) {
                fun action() = runs.forEach { adapter.write(project, it) }

                if (adapter.autoRun(project))
                    action()

                project.tasks.create(adapter.getTaskName()) { it.doLast { action() } }
            }
        }
    }

}
