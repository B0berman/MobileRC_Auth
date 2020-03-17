package com.mobilerc.registration.database

import com.mobilerc.registration.beans.User
import com.mobilerc.registration.database.dao.UserDAO


class MorphiaDAOFactory(db: MorphiaDB) : DAOFactory(db) {


    override val userDAO: DAO<User>
        get() = UserDAO(db)

}
