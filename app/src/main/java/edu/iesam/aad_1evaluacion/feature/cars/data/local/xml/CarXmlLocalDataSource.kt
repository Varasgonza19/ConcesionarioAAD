package edu.iesam.aad_1evaluacion.feature.cars.data.local.xml
import android.content.Context
import edu.iesam.aad_1evaluacion.R
import edu.iesam.aad_1evaluacion.core.serialization.JsonSerializer
import edu.iesam.aad_1evaluacion.feature.cars.domain.Car

class CarXmlLocalDataSource(
   private val context: Context,
    private val serializer: JsonSerializer<List<Car>>
) {

    companion object{
        val keyCar : String = "car"
    }

    val sharedPref = context.getSharedPreferences(context.getString(R.string.local_source_car),
        Context.MODE_PRIVATE)


        fun save(cars: List<Car>) {
            sharedPref.edit().apply {
                putString(keyCar, serializer.toJson(cars))
                apply()
            }

        }

        fun findAll(): List<Car> {
                val jsonList: String? = sharedPref.getString(keyCar, null)
                jsonList?.let {
                    return serializer.fromJson(it)
                }

            return emptyList()
        }

        fun deleteById(id: String) {
        save(findAll().filterNot { it.tuition == id })
        }

        fun findById(id: String): Car? {
            return findAll().find { it.tuition == id }
        }


        fun update(car: Car) {
            val cars = findAll().filterNot { it.tuition == car.tuition }.toMutableList()
            cars.add(car)
            save(cars)
        }
        }

