package com.br.car

import com.datastax.driver.core.Row

object CarRowMapper {
    fun build(row: Row) = Car(row.getUUID(0), row.getString(1), row.getString(2), row.getString(3))
}