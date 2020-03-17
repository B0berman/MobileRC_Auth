package com.mobilerc.registration.database

class DAOManager private constructor() {

    private val morphiaDB: MorphiaDB = MorphiaDB()
    private val morphiaDAOFactory: MorphiaDAOFactory

    init {
        morphiaDB.connect()
        morphiaDAOFactory = MorphiaDAOFactory(morphiaDB)
    }

    companion object {

        private val INSTANCE = DAOManager()

        val db: DataBase
            get() = INSTANCE.morphiaDB

        val factory: DAOFactory
            get() = INSTANCE.morphiaDAOFactory
    }
}
