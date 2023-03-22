package xyz.spruceloader.roots.mappings.mapper

interface Mapper {

    fun mapClass(name: String): String = name

    fun mapField(member: Member): String = member.name
    fun mapFieldMember(member: Member): Member = Member(mapClass(member.owner), mapField(member), mapDesc(member.desc))

    fun mapMethod(member: Member): String = member.name
    fun mapMethodMember(member: Member): Member =
        Member(mapClass(member.owner), mapMethod(member), mapMethodDesc(member.desc))

    fun mapDesc(desc: String): String {
        if (!(desc.startsWith("L") && desc.endsWith(";"))) {
            if (desc.length != 1 || !"BCDFIJSZV".contains(desc))
                throw IllegalArgumentException(desc)

            return desc
        }

        return 'L' + mapClass(desc.substring(1, desc.length - 1)) + ';'
    }

    fun mapMethodDesc(desc: String): String {
        require(desc.startsWith('('))
        require(desc.contains(')'))

        val result = StringBuilder()
        var params = desc.substring(1, desc.indexOf(')'))
        val type = desc.substring(desc.indexOf(')') + 1)
        require(type.isNotEmpty())

        while (params.isNotEmpty()) {
            params = params.substring(1)
            if (params[0] != 'L')
                result.append(mapDesc(params[0].toString()))
            else {
                val clazz = params.takeWhile { it != ';' } + ';'
                if (clazz.contains(')'))
                    throw IllegalArgumentException(desc)

                result.append(mapDesc(clazz))
            }
        }

        result.append(mapDesc(type))
        return result.toString()
    }

    fun reversed(): Mapper

}