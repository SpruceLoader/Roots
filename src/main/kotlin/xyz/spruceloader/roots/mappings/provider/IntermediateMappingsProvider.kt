package xyz.spruceloader.roots.mappings.provider

import net.fabricmc.mappingio.tree.MappingTree
import xyz.spruceloader.roots.mappings.Mapper

interface IntermediateMappingsProvider {

    /**
     * Provides the intermediate mappings.
     * @return official -> intermediate
     */
    fun provide(): Mapper

}