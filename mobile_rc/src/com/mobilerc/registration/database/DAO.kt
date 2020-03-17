package com.mobilerc.registration.database

import org.mongodb.morphia.query.Query

interface DAO<T> {

    val all: List<T>
    val first: T?
    fun push(bean: T): Boolean
    fun remove(bean: T): Boolean
    fun <K> filter(query: String, value: K): DAO<T>
    fun clear()
    fun retrievedFields(query: Boolean, value: String): Query<T>
    fun order(query: String): DAO<T>
    fun contains(field: String, query: String): DAO<T>
    fun limit(value: Int): DAO<T>
    fun offset(value: Int): DAO<T>
    fun or(field: String, value1: Any, value2: Any): DAO<T>
    fun <K> `in`(query: String, value: K): DAO<T>
}
