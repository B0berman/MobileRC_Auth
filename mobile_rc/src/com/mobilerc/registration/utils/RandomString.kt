package com.mobilerc.registration.utils

import java.security.SecureRandom
import java.util.Locale
import java.util.Objects
import java.util.Random

class RandomString @JvmOverloads constructor(length: Int, random: Random = SecureRandom(), symbols: String = alphanum) {

    private val random: Random

    private val symbols: CharArray

    private val buf: CharArray

    /**
     * Generate a random string.
     */
    fun nextString(): String {
        for (idx in buf.indices)
            buf[idx] = symbols[random.nextInt(symbols.size)]
        return String(buf)
    }

    init {
        if (length < 1)
            throw IllegalArgumentException()
        if (symbols.length < 2)
            throw IllegalArgumentException()
        this.random = Objects.requireNonNull(random)
        this.symbols = symbols.toCharArray()
        this.buf = CharArray(length)
    }

    constructor() : this(4, SecureRandom(), digits) {}

    companion object {

        val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        val lower = upper.toLowerCase(Locale.ROOT)

        val digits = "0123456789"

        val alphanum = upper + lower + digits
    }

    /**
     * Create session identifiers.
     */

}
/**
 * Create an alphanumeric string generator.
 */
/**
 * Create an alphanumeric strings from a secure generator.
 */
