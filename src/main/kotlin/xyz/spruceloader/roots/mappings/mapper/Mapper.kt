package xyz.spruceloader.roots.mappings.mapper

interface Mapper {

    fun mapClass(name: String): String = name

    fun mapField(member: Member): String = member.name

    fun mapMethod(member: Member): String = member.name

}