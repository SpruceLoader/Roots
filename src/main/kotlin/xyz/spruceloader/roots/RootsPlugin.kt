package xyz.spruceloader.roots

import org.gradle.api.Project
import org.gradle.api.Plugin
import xyz.spruceloader.roots.extension.impl.RootsExtensionImpl

class RootsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("roots", RootsExtensionImpl::class.java)
    }

}
