package com.br.car

class CarNotFoundException : RuntimeException() {
    override val message: String
        get() = "Car not found"
}