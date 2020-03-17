package com.mobilerc.registration.database

import com.mobilerc.registration.beans.User


abstract class DAOFactory(protected var db: DataBase) {

    abstract val userDAO: DAO<User>
}
