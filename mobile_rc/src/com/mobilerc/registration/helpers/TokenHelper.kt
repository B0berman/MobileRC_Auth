package com.mobilerc.registration.helpers

import com.mobilerc.registration.beans.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.sql.Timestamp

import java.util.Date

fun generateAccessToken(user: User, aClass: Class<*>) : String = Jwts.builder() // VALIDITY 1 DAY
        .setExpiration(Timestamp(System.currentTimeMillis() + 1 * 60 * 60 * 24 * 1000))
        .claim("email", user.email)
        .claim("role", "BASIC")
        .setIssuer(getProperty("token_issuer", aClass, "keys"))
        .setSubject(user.id)
        .signWith(SignatureAlgorithm.HS256, getProperty("signing_key", aClass, "keys"))
        .compact()

fun generateSuperAdminToken(email: String, aClass: Class<*>) : String = Jwts.builder() // VALIDITY 1 DAY
        .setExpiration(Timestamp(System.currentTimeMillis() + 1 * 60 * 60 * 24 * 1000))
        .claim("email", email)
        .claim("role", "SUPER_ADMIN")
        .setIssuer(getProperty("token_issuer", aClass, "keys"))
        .signWith(SignatureAlgorithm.HS256, getProperty("signing_key", aClass, "keys"))
        .compact()

fun getTokenClaims(token: String, aClass: Class<*>): Claims {
    return Jwts.parser()
            .setSigningKey(getProperty("signing_key", aClass, "keys"))
            .parseClaimsJws(token)
            .body
}
