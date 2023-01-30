package fr.isen.perigot.androiderestaurant

import android.content.ClipData
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import fr.isen.perigot.androiderestaurant.modele.Items
import java.io.File
import java.io.Serializable


const val BASKET_FILE = "basket.json"
const val USER_PREFERENCE_NAME = "USER_PREFERENCE_NAME"
const val ITEMS_COUNT = "ITEMS_COUNT"

class BasketItem(val dish: Items, var quantity: Int): Serializable {}
class Basket(val items: MutableList<BasketItem>): Serializable {


    val itemsCount: Int
        get () {
            val count = items.map {
                it.quantity
            }.reduce {
                    acc, i -> acc + i
            }
            return count
        }

    private fun updateCounter(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
        Log.d(ITEMS_COUNT,itemsCount.toString())
    }

    fun addItem(item: Items, quantity: Int) {
        val existingItem = items.firstOrNull {
            it.dish.nameFr == item.nameFr
        }
        existingItem?.let {
            existingItem.quantity += quantity
        } ?:
        run {
            val basketItem = BasketItem(item, quantity)
            items.add(basketItem)
        }
    }
    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        jsonFile.writeText(GsonBuilder().create().toJson(this))
        updateCounter(context)
    }

    fun removeDish(basketItem: BasketItem) {
        items.remove(basketItem)

    }

/* tentative pour faire le total du panier
    val total:Double
        get() {
            var total = 0.0
            items.forEach {
                total += it.dish.prices[0].price?.toDouble() ?: 0.0 * it.quantity

            }
            return total
        }*/

    companion object {
        fun getBasket(context: Context): Basket {
            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            if (jsonFile.exists()) {
                val json = jsonFile.readText()
                return GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                return Basket(mutableListOf())
            }
        }
    }

}
