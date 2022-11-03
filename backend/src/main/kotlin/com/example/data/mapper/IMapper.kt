package com.example.data.mapper

interface IMapper<Entity, Model> {

    fun toModel(entity: Entity): Model
    fun toEntity(model: Model): Entity = throw NotImplementedError("Method not implemented")

    fun toListEntity(entities: MutableList<Model>): MutableList<Entity> {
        val list = mutableListOf<Entity>()
        entities.mapTo(list) { toEntity(it) }
        return list
    }

    fun toListModel(entities: MutableList<Entity>): MutableList<Model> {
        val list = mutableListOf<Model>()
        entities.mapTo(list) { toModel(it) }
        return list
    }
}
