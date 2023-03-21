package xyz.spruceloader.roots

import org.gradle.api.Project
import org.gradle.api.Plugin

class RootsPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'xyz.spruceloader.roots.greeting'")
            }
        }
    }
}
