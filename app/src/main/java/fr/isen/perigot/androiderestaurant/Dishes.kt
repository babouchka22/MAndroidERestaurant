package fr.isen.perigot.androiderestaurant
import java.util.*

class Dishes (val name: String){

        companion object {

            fun addDish(nom: Array<String>): ArrayList<Dish> {
                val dish = ArrayList<Dish>()
                for(value in nom){
                    dish.add(Dishes(value))
                }

                return dish
            }
        }
    }
}