package com.br.car

import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CarService(
    private val carDAO: CarDAO
) {

    fun save(car: Car) = carDAO.save(car)

    fun findByColor(color: String?) = color?.let { carDAO.findByColor(it) } ?: carDAO.findAll()

    fun findById(id: UUID): Car {
        return carDAO.findById(id) ?: throw CarNotFoundException()
    }
}