package com.mobilerc.registration.database.dao

import com.mobilerc.registration.beans.User
import com.mobilerc.registration.database.DataBase
import com.mobilerc.registration.database.MorphiaDAO

class UserDAO(db: DataBase) : MorphiaDAO<User>(db)
