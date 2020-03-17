package com.mobilerc.registration.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord.CreateRequest
import com.google.firebase.database.FirebaseDatabase
import com.mobilerc.registration.beans.User
import com.mobilerc.registration.beans.dto.ResponseDTO
import com.mobilerc.registration.beans.dto.UserCreationDTO
import com.mobilerc.registration.database.DAOManager
import com.mobilerc.registration.helpers.emailExists
import java.util.*


class RegisterService {

    fun create(userDTO: UserCreationDTO): ResponseDTO {
        if (userDTO.email.isEmpty() || userDTO.firstName.isEmpty() || userDTO.lastName.isEmpty())
            return ResponseDTO(error = "All fields must be filled.")
        if (emailExists(userDTO.email))
            return ResponseDTO(error = "This user email is already used")

        val user = User(userDTO)

        DAOManager.factory.userDAO.push(user)
        val request = CreateRequest().setUid(user.id)
                .setEmail(user.email)
                .setEmailVerified(true)
                .setPassword(userDTO.password)
                .setDisplayName(user.firstName + " " + user.lastName)
        try {
            FirebaseAuth.getInstance().createUser(request)
            val userParams: MutableMap<String, String> = HashMap()
            userParams["email"] = user.email
            userParams["displayName"] = user.firstName + " " + user.lastName
            FirebaseDatabase.getInstance().getReference("users").child(user.id).setValueAsync(userParams)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
        }
/*        val code: String = RandomString().nextString()
        val subject = "Code de validation"
        val message = "Utilisez le code suivant pour valider votre compte : $code"
        user.validationCode = encryptCode(this.javaClass, code)
        if (!sendEmail(user.email, subject, message, null, this.javaClass))
            return ResponseDTO(error = "Error sending email.")*/
        return ResponseDTO(data = user)
    }

 /*   fun validateAccount(validationDTO: CodeValidationDTO): ResponseDTO {
        if (validationDTO.email.isEmpty() || validationDTO.code.isEmpty() || validationDTO.password.isEmpty())
            return ResponseDTO(error = "All fields must be filled.")
        val admin: Admin = DAOManager.factory.adminDAO.filter("email", validationDTO.email).first
                ?: return ResponseDTO(error = "Invalid email")
        return if (isValidCode(validationDTO.code, admin, this.javaClass)) {
            admin.validated = true
            admin.validationCode = ""
            admin.setHashedPassword(validationDTO.password)
            DAOManager.factory.adminDAO.push(admin)
            var isSuper: Boolean? = null
            var token = ""
            if (getProperty("super_admin", this::class.java, "right").contains(admin.email)) {
                token = generateSuperAdminToken(admin.email, this::class.java)
                isSuper = true
            } else
                token = generateAccessToken(admin, this::class.java)

            val dto = AdminDTO(email = admin.email, lastname = admin.lastname, firstname = admin.firstname, token = token, id = admin.id, isSuper = isSuper)
            ResponseDTO(data = dto)
        } else
            ResponseDTO(error = "Invalid code")
    }*/
}
