package com.mobilerc.registration.helpers

import com.mobilerc.registration.database.DAOManager
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.IOException
import java.lang.Long
import java.nio.charset.Charset
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

const val DAY_TIMESTAMP = 100 * 60 * 60 * 24

fun convertLongToTime(time: Long): String {

    val date = Date()
    date.time = time as kotlin.Long
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(date)
}

fun getCredentialParam(credential: String?, pos: Int): String? {
    if (credential == null) {
        return null
    }
    var decodedCredential: String? = null
    try {
        val decodedBytes = Base64.getDecoder().decode(credential)
        decodedCredential = String(decodedBytes, Charset.forName("UTF-8"))
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

    val tokenizer = StringTokenizer(decodedCredential, ":")
    var param1 = ""
    var param2 = ""
    if (tokenizer.hasMoreTokens()) {
        param1 = tokenizer.nextToken()
    }
    if (tokenizer.hasMoreTokens()) {
        param2 = tokenizer.nextToken()
    }
    return if (pos == 1) param1 else param2
}

fun getProperty(key: String, aClass: Class<*>, file: String): String {
    val props = Properties()
    try {
        val inputStream = aClass.classLoader.getResourceAsStream("$file.properties")
        props.load(inputStream!!)
        return props.getProperty(key)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return ""
}

fun emailExists(email: String) : Boolean {
    DAOManager.factory.userDAO.filter("email", email).first
            ?: DAOManager.factory.userDAO.filter("email", email).first ?: return false
    return true
}

fun encryptCode(aClass: Class<*>, code: String): String = Jwts.builder() // VALIDITY 1 DAY
        .setExpiration(Timestamp(System.currentTimeMillis() + 1 * 60 * 60 * 24 * 1000))
        .claim("code", code)
        .signWith(SignatureAlgorithm.HS256, getProperty("signing_key", aClass, "keys"))
        .compact()

/*fun isValidCode(code: String, user: User, aClass: Class<*>): Boolean {
    return try {
        val key: String = getProperty("signing_key", aClass, "keys")
        Date(Long.valueOf(Jwts.parser().setSigningKey(key).parseClaimsJws(user.validationCode).body["exp"].toString()) * 1000)
        val employeeCodeActivation = Jwts.parser().setSigningKey(key).parseClaimsJws(user.validationCode).body["code"].toString()
        if (code == employeeCodeActivation) {
            return true
        }
        false
    } catch (e: Exception) {
        false
    }
}*/


/// TODO refaire token gestion


