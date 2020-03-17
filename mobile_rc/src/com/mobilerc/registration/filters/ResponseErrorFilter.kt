package com.mobilerc.registration.filters

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.ext.Provider

@Provider
class ResponseErrorFilter : ContainerResponseFilter {

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        /*if (responseContext.entity is String && (responseContext.entity as String).contains("Unrecognized field")) {
            println(responseContext.entity)
            responseContext.entity = "Error catched : Unprocessable entity."
            responseContext.status = 422
        }
        if (responseContext.status != 400 && responseContext.status != 401 && responseContext.status != 200) {
            println(responseContext.entity)
            responseContext.entity = "Error catched : Unprocessable entity."
            responseContext.status = 422
        }*/
    }
}
