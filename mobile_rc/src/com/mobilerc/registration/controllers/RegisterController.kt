package com.mobilerc.registration.controllers

import com.mobilerc.registration.beans.dto.UserCreationDTO
import com.mobilerc.registration.services.RegisterService
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/register")
class RegisterController {

    private val registerService: RegisterService = RegisterService()

    @POST
    fun register(userDTO: UserCreationDTO): Response? {
        val response = registerService.create(userDTO)
        return response.buildResponse()
    }

 /*   @POST
    @Path("/validate")
    @ApiOperation(value = "Validate an account.", response = ResponseDTO::class)
    fun validateAccount(validationDTO: CodeValidationDTO): Response? {
        val response = registerService.validateAccount(validationDTO)
        return response.buildResponse()
     }

  */
}
