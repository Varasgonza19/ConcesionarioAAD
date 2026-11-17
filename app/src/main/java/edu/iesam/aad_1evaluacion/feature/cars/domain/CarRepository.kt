package edu.iesam.aad_1evaluacion.feature.cars.domain

interface CarRepository {
    fun getAll(car : List<Car>)
}