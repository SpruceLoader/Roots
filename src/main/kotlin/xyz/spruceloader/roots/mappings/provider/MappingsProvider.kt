package xyz.spruceloader.roots.mappings.provider

import xyz.spruceloader.roots.mappings.mapper.Mapper

interface MappingsProvider {

    /**
     * Provides the mappings.
     * @param intermediate official -> intermediate
     * @return intermediate -> named
     */
    fun provide(gameVersion: String, intermediate: Mapper): Mapper

}