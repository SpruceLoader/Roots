package xyz.spruceloader.roots

import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.RootsExtension

class RootsPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("roots", RootsExtension::class.java)
    }

}
