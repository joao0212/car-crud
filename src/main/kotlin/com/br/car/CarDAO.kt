package com.br.car

import com.br.config.ClusterScyllaConfig
import com.datastax.driver.core.Session
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CarDAO(
    private val session: Session =
        ClusterScyllaConfig.buildCluster().connect("automobilies")
) {
    fun save(car: Car) {
        val ps = session.prepare("INSERT INTO car (id, color, brand, model) VALUES (?, ?, ?, ?)")
        val bs = ps.bind(car.id, car.color, car.brand, car.model)
        session.execute(bs)
    }

    fun findAll() : List<Car> {
        val ps = session.prepare("SELECT * FROM car")
        val bs = ps.bind()
        val rs = session.execute(bs)

        return rs.map {
            CarRowMapper.build(it)
        }
    }

    fun findByColor(color: String) : List<Car> {
        val ps = session.prepare("SELECT * FROM car WHERE color = ?")
        val bs = ps.bind(color)
        val rs = session.execute(bs)

        return rs.map {
            CarRowMapper.build(it)
        }
    }
}