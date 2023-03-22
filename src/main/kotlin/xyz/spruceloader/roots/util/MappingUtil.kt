package xyz.spruceloader.roots.util

import net.fabricmc.mappingio.MappingReader
import net.fabricmc.mappingio.tree.MappingTree
import net.fabricmc.mappingio.tree.MemoryMappingTree
import java.io.InputStream
import java.io.InputStreamReader
import java.util.zip.ZipInputStream

fun extractTinyMappings(input: InputStream): MappingTree {
    val result = MemoryMappingTree()
    val zip = ZipInputStream(input)
    generateSequence(zip::getNextEntry).first { it.name == "mappings/mappings.tiny" }.also {
        MappingReader.read(InputStreamReader(zip), result)
    }
    return result
}