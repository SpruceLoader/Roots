package xyz.spruceloader.roots.extension

import groovy.lang.Closure
import org.gradle.api.Action
import xyz.spruceloader.roots.run.RunConfig

interface RunConfigs {

    fun create(name: String, creator: Action<RunConfig>)

}