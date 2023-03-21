package xyz.spruceloader.roots.mappings.impl

import net.fabricmc.mappingio.MappingReader
import net.fabricmc.mappingio.tree.MappingTree
import net.fabricmc.mappingio.tree.MemoryMappingTree
import xyz.spruceloader.roots.mappings.MappingsProvider
import java.io.InputStreamReader
import java.net.URL

class YarnMappingsProvider(private val gameVersion: String, private val build: Int) : MappingsProvider {

    override fun provide(): MappingTree {
        val version = "$gameVersion%2Bbuild.$build"
        val url = URL("https://maven.fabricmc.net/net/fabricmc/yarn/$version/yarn-$version-v2.jar")
        val result = MemoryMappingTree()
        InputStreamReader(url.openStream()).use {
            MappingReader.read(it, result)
        }
        return result
    }

}