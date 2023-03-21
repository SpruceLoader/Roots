package xyz.spruceloader.roots.mappings

import net.fabricmc.mappingio.tree.MappingTree

interface MappingsProvider {

    fun provide(): MappingTree

}