package xyz.spruceloader.roots.mappings.provider.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider

object OfficialIntermediate : IntermediateMappingsProvider {

    override fun provide(gameVersion: String): Mapper = object : Mapper {}

}