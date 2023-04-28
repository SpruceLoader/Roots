package xyz.spruceloader.roots.dependency

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.FileCollectionDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.SelfResolvingDependency
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.artifacts.dependencies.AbstractModuleDependency
import org.gradle.api.tasks.TaskDependency
import java.io.File

class MinecraftDependency(private val version: String) : SelfResolvingDependency, FileCollectionDependency {

    private var reason: String? = null

    override fun getGroup(): String = "xyz.spruceloader.roots.generated"

    override fun getName(): String = "minecraft"

    override fun getVersion(): String = version

    override fun contentEquals(dependency: Dependency): Boolean {
        TODO("Not yet implemented")
    }

    override fun copy(): Dependency = MinecraftDependency(version)

    override fun getReason(): String? = reason

    override fun because(reason: String?) {
        this.reason = reason;
    }

    override fun getBuildDependencies(): TaskDependency {
        TODO("Not yet implemented")
    }

    override fun resolve(): MutableSet<File> {
        TODO("Not yet implemented")
    }

    override fun resolve(transitive: Boolean): MutableSet<File> {
        TODO("Not yet implemented")
    }

    override fun getFiles(): FileCollection {
        TODO("Not yet implemented")
    }
}
