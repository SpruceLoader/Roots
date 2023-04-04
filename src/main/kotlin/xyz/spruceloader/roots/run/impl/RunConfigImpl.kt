package xyz.spruceloader.roots.run.impl

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider
import xyz.spruceloader.roots.mappings.provider.MappingsProvider
import xyz.spruceloader.roots.run.RunConfig
import javax.inject.Inject

class RunConfigImpl(private val name: String, objectFactory: ObjectFactory) : RunConfig {

    private val mainClass: Property<String> = objectFactory.property(String::class.java)
    private val args: ListProperty<String> = objectFactory.listProperty(String::class.java)
    private val jvmArgs: ListProperty<String> = objectFactory.listProperty(String::class.java)

    override fun name(): String = name
    override fun mainClass(): Property<String> = mainClass
    override fun args(): ListProperty<String> = args
    override fun jvmArgs(): ListProperty<String> = jvmArgs

}