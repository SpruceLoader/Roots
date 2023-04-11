package xyz.spruceloader.roots.run.adapter.impl

import org.gradle.api.Project
import xyz.spruceloader.roots.run.RunConfig
import xyz.spruceloader.roots.run.adapter.RunConfigAdapter
import xyz.spruceloader.roots.util.xmlEscapeList
import java.nio.file.Files
import java.nio.file.Path

object IntelliJAdapter : RunConfigAdapter {

    override fun write(project: Project, config: RunConfig, ignoredFolder: Lazy<Path>) {
        val folder = project.projectDir.toPath().resolve(".idea/runConfigurations")
        if (!Files.isDirectory(folder))
            Files.createDirectories(folder)

        val file = folder.resolve(config.getName() + ".xml")

        Files.newOutputStream(file).use {
            it.write("""
                <component name="ProjectRunConfigurationManager">
                    <configuration default="false" name="%NAME%" type="Application" factoryName="Application">
                        <option name="MAIN_CLASS_NAME" value="${config.getMainClass()}"/>
                        <module name="%IDEA_MODULE%"/>
                        <option name="PROGRAM_PARAMETERS" value="${xmlEscapeList(config.getArgs())}"/>
                        <option name="VM_PARAMETERS" value="${xmlEscapeList(config.getJvmArgs())}"/>
                        <option name="WORKING_DIRECTORY" value="${'$'}PROJECT_DIR${'$'}/%RUN_DIRECTORY%/"/>
                        <method v="2">
                            <option name="Make" enabled="true"/>
                        </method>
                        <shortenClasspath name="ARGS_FILE"/>
                    </configuration>
                </component>
            """.trimIndent().toByteArray())
        }
    }

    override fun getTaskName(): String = "genIntelliJRuns"

    override fun autoRun(project: Project): Boolean = System.getProperty("idea.version") != null

}