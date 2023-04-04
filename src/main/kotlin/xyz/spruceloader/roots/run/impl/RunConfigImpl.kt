package xyz.spruceloader.roots.run.impl

import xyz.spruceloader.roots.run.RunConfig
import java.lang.UnsupportedOperationException

class RunConfigImpl(private val name: String) : RunConfig {

    private var mainClass: String? = null
    private var args: Array<out String> = emptyArray()
    private var jvmArgs: Array<out String> = emptyArray()

    override fun getName(): String = name

    override fun getMainClass() = mainClass ?: throw UnsupportedOperationException("Incomplete run config; missing main class")

    override fun mainClass(className: String) {
        mainClass = className
    }

    override fun getArgs(): Array<out String> = args

    override fun args(vararg args: String) {
        this.args = args
    }

    override fun getJvmArgs(): Array<out String> = jvmArgs

    override fun jvmArgs(vararg args: String) {
        jvmArgs = args
    }

}