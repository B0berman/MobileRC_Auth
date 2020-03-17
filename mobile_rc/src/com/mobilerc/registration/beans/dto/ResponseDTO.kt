package com.mobilerc.registration.beans.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import javax.ws.rs.core.Response

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseDTO(var error: Any? = null, var data: Any? = null, @JsonIgnore var errorCode: Int? = null, @JsonIgnore var filename: String? = null) {

    fun buildResponse() : Response = if (error == null)
        Response.ok(this).build()
    else {
        when (errorCode) {
            401 -> Response.status(Response.Status.UNAUTHORIZED).entity(this).build()
            else -> Response.status(Response.Status.BAD_REQUEST).entity(this).build()
        }
    }

    fun buildPdfResponse() : Response =
            Response.ok(data).header("Content-Disposition", "attachment; filename=${filename}").build()
}
