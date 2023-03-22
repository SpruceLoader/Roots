package xyz.spruceloader.roots.mappings.provider

import xyz.spruceloader.roots.mappings.mapper.Mapper

interface MappingsProvider {

    fun provide(gameVersion: String, intermediateProvider: IntermediateMappingsProvider): Mapper

}