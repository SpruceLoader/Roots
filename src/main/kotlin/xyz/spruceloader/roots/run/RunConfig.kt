package xyz.spruceloader.roots.run

import org.gradle.api.provider.Property
import org.gradle.api.provider.ListProperty
import java.util.*

interface RunConfig {

    fun name(): String

    fun mainClass(): Property<String>
    fun mainClass(className: String) = mainClass().set(className)

    fun args(): ListProperty<String>
    fun args(vararg args: String) = jvmArgs().value(listOf(args) as Iterable<String>)

    fun jvmArgs(): ListProperty<String>
    fun jvmArgs(vararg args: String) = jvmArgs().value(listOf(args) as Iterable<String>)

}