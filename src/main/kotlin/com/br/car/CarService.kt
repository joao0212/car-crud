package com.br.car

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CarService(
    private val carDAO: CarDAO
) {

    fun save(car: Car) = carDAO.save(car)

    fun findByColor(color: String?) : List<Car> {
        return color?.let {
            carDAO.findByColor(it)
        } ?: carDAO.findAll()
    }
}