package xyz.spruceloader.roots.run.adapter.impl

import org.gradle.api.Project
import org.gradle.plugins.ide.eclipse.model.EclipseModel
import xyz.spruceloader.roots.run.RunConfig
import xyz.spruceloader.roots.run.adapter.RunConfigAdapter
import xyz.spruceloader.roots.util.xmlEscapeList
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

object EclipseAdapter : RunConfigAdapter {

    override fun write(project: Project, config: RunConfig, folder: Lazy<Path>) {
        if (!Files.isDirectory(folder.value))
            Files.createDirectories(folder.value)
        val name = project.extensions.findByType(EclipseModel::class.java)?.project?.name ?: project.name
        val file = folder.value.resolve(project.name + ".launch")

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
                    <stringAttribute key="org.eclipse.jdt.launching.PROGRAM_ARGUMENTS" value="${xmlEscapeList(config.getArgs())}"/>
                    <stringAttribute key="org.eclipse.jdt.launching.PROJECT_ATTR" value="$name"/>
                    <stringAttribute key="org.eclipse.jdt.launching.VM_ARGUMENTS" value="${xmlEscapeList(config.getJvmArgs())}"/>
                    <stringAttribute key="org.eclipse.jdt.launching.WORKING_DIRECTORY" value="${'$'}{workspace_loc:$name}/%RUN_DIRECTORY%"/>
                    <booleanAttribute key="org.eclipse.jdt.launching.ATTR_ATTR_USE_ARGFILE" value="true"/>
                </launchConfiguration>
            """.trimIndent().toByteArray(StandardCharsets.UTF_8))
        }
    }

    override fun getTaskName(): String = "genEclipseRuns"

    override fun autoRun(project: Project): Boolean = System.getProperty("eclipse.application") != null

}