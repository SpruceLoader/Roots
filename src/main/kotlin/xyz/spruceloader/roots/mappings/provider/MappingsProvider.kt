package xyz.spruceloader.roots.mappings.provider

import net.fabricmc.mappingio.tree.MappingTree
import xyz.spruceloader.roots.mappings.Mapper

interface MappingsProvider {

    /**
     * Provides the mappings.
     * @param intermediate official -> intermediate
     * @return intermediate -> named
     */
    fun provide(intermediate: Mapper): Mapper

}