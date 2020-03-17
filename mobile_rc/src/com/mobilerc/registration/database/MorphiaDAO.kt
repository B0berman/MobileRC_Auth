package com.mobilerc.registration.database

import org.bson.types.ObjectId
import org.mongodb.morphia.dao.BasicDAO
import org.mongodb.morphia.query.Query

open class MorphiaDAO<T> protected constructor(db: DataBase) : BasicDAO<T, ObjectId>((db as MorphiaDB).datastore), DAO<T> {

    private var query: Query<T>

    override val all: List<T>
        get() = query.asList()

    override val first: T
        get() = query.get()

    init {
        query = super.createQuery()
    }

    override fun clear() {
        val list = query.asList()
        for (entity in list) {
            super.delete(entity)
        }
    }

    override fun push(bean: T): Boolean {
        super.save(bean)
        return true
    }

    override fun remove(bean: T): Boolean {
        super.delete(bean)
        return true
    }

    override fun <K> filter(query: String, value: K): DAO<T> {
        this.query = this.query.filter(query, value)
        return this
    }

    override fun <K> `in`(query: String, value: K): DAO<T> {
        this.query = this.query.filter("$query in", value)
        return this
    }

    override fun order(query: String): DAO<T> {
        this.query = this.query.order(query)
        return this
    }

    override fun contains(field: String, query: String): DAO<T> {
        this.query = this.query.field(field).contains(query)
        return this
    }

    override fun limit(value: Int): DAO<T> {
        this.query = this.query.limit(value)
        return this
    }

    override fun offset(value: Int): DAO<T> {
        this.query = this.query.offset(value)
        return this
    }

    override fun or(field: String, value1: Any, value2: Any): DAO<T> {
        this.query.or(
                this.query.criteria(field).equal(value1),
                this.query.criteria(field).equal(value2))
        return this
    }

    override fun retrievedFields(query: Boolean, value: String): Query<T> {
        return this.query.retrievedFields(query, value)
    }
}
