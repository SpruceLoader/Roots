package xyz.spruceloader.roots.mappings

interface Mapper {

    fun mapClass(name: String): String

    fun mapField(member: Member): String

    fun mapMethod(member: Member): String

}