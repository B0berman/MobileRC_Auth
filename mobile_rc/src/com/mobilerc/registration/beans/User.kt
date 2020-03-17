package com.mobilerc.registration.beans

import com.mobilerc.registration.beans.dto.UserCreationDTO
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity(noClassnameStored = true, value = "users")
data class User(@Id val id : String = ObjectId.get().toString(),
                var email: String = "",
                var firstName: String = "",
                var lastName: String = "") {
    constructor(userDTO: UserCreationDTO) : this(email = userDTO.email)
}
