package xyz.spruceloader.roots.mappings.mapper.impl

import net.fabricmc.mappingio.tree.MappingTree
import xyz.spruceloader.roots.mappings.mapper.Mapper
import xyz.spruceloader.roots.mappings.mapper.Member

class TinyMapper(
    private val mappings: MappingTree,
    private val sourceNamespace: Int,
    private val destinationNamespace: Int
) : Mapper {

    constructor(mappings: MappingTree, sourceNamespace: String, destinationNamespace: String) : this(
        mappings,
        mappings.getNamespaceId(sourceNamespace),
        mappings.getNamespaceId(destinationNamespace)
    )

    override fun mapClass(name: String): String =
        mappings.getClass(name, sourceNamespace)?.getDstName(destinationNamespace) ?: name

    override fun mapField(member: Member): String =
        mappings.getField(member.owner, member.name, member.desc, sourceNamespace)
            ?.getDstName(destinationNamespace) ?: member.name

    override fun mapMethod(member: Member): String =
        mappings.getMethod(member.owner, member.name, member.desc, sourceNamespace)
            ?.getDstName(destinationNamespace) ?: member.name

    override fun reversed() = TinyMapper(mappings, destinationNamespace, sourceNamespace)

}