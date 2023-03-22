package xyz.spruceloader.roots

import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.RootsExtension
import xyz.spruceloader.roots.extension.impl.RootsExtensionImpl

class RootsPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'xyz.spruceloader.roots.greeting'")
            }
        }
        project.extensions.create("roots", RootsExtension::class.java)
    }

}
