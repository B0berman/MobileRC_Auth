package com.mobilerc.registration.database

interface DataBase {

    fun connect(): Boolean

    fun disconnect(): Boolean
}
