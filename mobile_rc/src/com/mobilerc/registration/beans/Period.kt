package com.mobilerc.registration.beans

import java.util.*

data class Period(var begin: Long = Date().time,
                  var end: Long = Date().time)
