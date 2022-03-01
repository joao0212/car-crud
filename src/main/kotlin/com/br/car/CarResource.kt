package com.br.car

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CarResource(
    private val carService: CarService
) {

    @POST
    fun save(car: Car) = carService.save(car)

    @GET
    fun findByColor(@QueryParam("color") color: String?) = carService.findByColor(color)
}