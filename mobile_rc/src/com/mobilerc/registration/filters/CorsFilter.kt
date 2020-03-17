package com.mobilerc.registration.filters

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.ext.Provider
import java.io.IOException

@Provider
class CorsFilter : ContainerResponseFilter {

    @Throws(IOException::class)
    override fun filter(containerRequestContext: ContainerRequestContext, containerResponseContext: ContainerResponseContext) {
        containerResponseContext.headers.add("Access-Control-Allow-Origin", "*")
        containerResponseContext.headers.add("Access-Control-Allow-Credentials", "true")
        containerResponseContext.headers.add("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE")
        containerResponseContext.headers.add("Access-Control-Max-Age", "3600")
        containerResponseContext.headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization")
    }
}
