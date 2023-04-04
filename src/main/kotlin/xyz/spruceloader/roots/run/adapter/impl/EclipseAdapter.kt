package xyz.spruceloader.roots.run.adapter.impl

import org.gradle.api.Project
import org.gradle.plugins.ide.eclipse.model.EclipseModel
import xyz.spruceloader.roots.run.RunConfig
import xyz.spruceloader.roots.run.adapter.RunConfigAdapter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.stream.Collectors

object EclipseAdapter : RunConfigAdapter {

    override fun write(project: Project, config: RunConfig) {
        val folder = project.projectDir.toPath().resolve("eclipse-runs")
        if (!Files.isDirectory(folder))
            Files.createDirectories(folder)
        val name = project.extensions.findByType(EclipseModel::class.java)?.project?.name ?: project.name
        val file = folder.resolve(project.name + ".launch")

        // from loom
        Files.newOutputStream(file).use {
            it.write("""
                <?xml version="1.0" encoding="UTF-8" standalone="no"?>
                <launchConfiguration type="org.eclipse.jdt.launching.localJavaApplication">
                    <listAttribute key="org.eclipse.debug.core.MAPPED_RESOURCE_PATHS">
                        <listEntry value="/$name"/>
                    </listAttribute>
                    <listAttribute key="org.eclipse.debug.core.MAPPED_RESOURCE_TYPES">
                        <listEntry value="4"/>
                    </listAttribute>
                    <booleanAttribute key="org.eclipse.jdt.launching.ATTR_USE_START_ON_FIRST_THREAD" value="true"/>
                    <stringAttribute key="org.eclipse.jdt.launching.CLASSPATH_PROVIDER" value="org.eclipse.buildship.core.classpathprovider"/>
                    <stringAttribute key="org.eclipse.jdt.launching.MAIN_TYPE" value="${config.getMainClass()}"/>
                    <stringAttribute key="org.eclipse.jdt.launching.PROGRAM_ARGUMENTS" value="${joinArguments(config.getArgs())}"/>
                    <stringAttribute key="org.eclipse.jdt.launching.PROJECT_ATTR" value="$name"/>
                    <stringAttribute key="org.eclipse.jdt.launching.VM_ARGUMENTS" value="${joinArguments(config.getJvmArgs())}"/>
                    <stringAttribute key="org.eclipse.jdt.launching.WORKING_DIRECTORY" value="${'$'}{workspace_loc:$name}/%RUN_DIRECTORY%"/>
                    <booleanAttribute key="org.eclipse.jdt.launching.ATTR_ATTR_USE_ARGFILE" value="true"/>
                </launchConfiguration>
            """.trimIndent().toByteArray(StandardCharsets.UTF_8))
        }
    }

    override fun getTaskName(): String = "genEclipseRuns"

    override fun autoRun(project: Project): Boolean = System.getProperty("eclipse.application") != null

    fun joinArguments(arguments: Array<out String>): String {
        return arguments.asSequence().map {
            if (it.contains(' '))
                return@map "&quot;$it&quot;"
            it
        }.joinToString(" ")
    }

}