package xyz.spruceloader.roots.mappings.provider.impl.intermediate

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.provider.IntermediateMappingsProvider

object NoIntermediate : IntermediateMappingsProvider {

    override fun provide(gameVersion: String): Mapper = object : Mapper {}

}