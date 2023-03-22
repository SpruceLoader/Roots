package xyz.spruceloader.roots.mappings.provider

import xyz.spruceloader.roots.mappings.mapper.Mapper

interface IntermediateMappingsProvider {

    /**
     * Provides the intermediate mappings.
     * @return official -> intermediate
     */
    fun provide(gameVersion: String): Mapper

}