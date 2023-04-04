package xyz.spruceloader.roots.run.impl

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import xyz.spruceloader.roots.run.RunConfig
import javax.inject.Inject

open class RunConfigImpl @Inject constructor(project: Project, private val name: String) : RunConfig {

    private val mainClass: Property<String> = project.objects.property(String::class.java)
    private val args: ListProperty<String> = project.objects.listProperty(String::class.java)
    private val jvmArgs: ListProperty<String> = project.objects.listProperty(String::class.java)

    override fun name(): String = name
    override fun mainClass(): Property<String> = mainClass
    override fun args(): ListProperty<String> = args
    override fun jvmArgs(): ListProperty<String> = jvmArgs

}