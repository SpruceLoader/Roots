package xyz.spruceloader.roots.mappings.mapper.impl

import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.Member

class ProxyMapper(private val first: Mapper, private val then: Mapper) : Mapper {

    override fun mapClass(name: String): String = then.mapClass(first.mapClass(name))

    override fun mapField(member: Member): String = then.mapMethod(first.mapFieldMember(member))

    override fun mapMethod(member: Member): String = then.mapMethod(first.mapMethodMember(member))

    override fun reversed(): Mapper = throw NotImplementedError()

}