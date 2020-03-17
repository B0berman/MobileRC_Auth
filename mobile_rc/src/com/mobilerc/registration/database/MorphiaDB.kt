package com.mobilerc.registration.database

import com.mongodb.MongoClient
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia

class MorphiaDB : DataBase {

    private var morphia: Morphia = Morphia()
    private var client: MongoClient? = null
    var datastore: Datastore? = null

    private val url = "localhost"//"db_mongo_velmys"

    override fun connect(): Boolean {
        client = MongoClient(url)//, 7017)
        morphia = Morphia()
        datastore = morphia.createDatastore(client!!, "mobilercDB")
        return true
    }

    override fun disconnect(): Boolean {
        client!!.close()
        return true
    }

}
