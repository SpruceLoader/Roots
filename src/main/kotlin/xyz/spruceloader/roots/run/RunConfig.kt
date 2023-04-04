package xyz.spruceloader.roots.run

interface RunConfig {

    fun getName(): String

    fun getMainClass(): String

    fun mainClass(className: String)

    fun getArgs(): Array<out String>

    fun args(vararg args: String)

    fun getJvmArgs(): Array<out String>

    fun jvmArgs(vararg args: String)

}