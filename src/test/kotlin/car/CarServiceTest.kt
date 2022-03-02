package car

import com.br.car.Car
import com.br.car.CarDAO
import com.br.car.CarNotFoundException
import com.br.car.CarService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CarServiceTest {

    private val cars = listOf(Car(UUID.fromString("6726f21d-5c75-4250-b2e7-062b4fad7dab"), "VW", "Red", "Golf"))

    private val carDAO: CarDAO = mockk {
        every { findAll() } returns cars
        every { findByColor(any()) } returns cars
    }

    private val carService = CarService(carDAO)

    @Test
    fun `should list all cars when color is null`() {
        carService.findByColor(null);

        verify(exactly = 1) { carDAO.findAll() }
        verify(inverse = true) { carDAO.findByColor(any()) }
    }

    @Test
    fun `should list cars when color is not null`() {
        val car = carService.findByColor("Red");

        verify(inverse = true) { carDAO.findAll() }
        verify(exactly = 1) { carDAO.findByColor(any()) }

        assertThat(car.first().color).isEqualTo("Red")
        assertThat(car.first().model).isEqualTo("Golf")
    }

    @Test
    fun `should call car not found exception`() {
        every { carDAO.findById(any()) } returns null

        val actual = assertThrows<CarNotFoundException> {
            carService.findById(UUID.fromString("af93412f-8257-4eaf-809b-fbd96a281678"))
        }

        assertThat(actual.message).isEqualTo("Car not found")
    }
}