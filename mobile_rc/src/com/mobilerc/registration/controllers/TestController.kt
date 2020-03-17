package com.mobilerc.registration.controllers

import com.mobilerc.registration.beans.dto.ResponseDTO

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
class TestController {
    @Context
    private var servletRequest: HttpServletRequest? = null

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun baseTest(): Response {
        return Response.ok(ResponseDTO(data = "API RUNS !!")).build()
    }
}
