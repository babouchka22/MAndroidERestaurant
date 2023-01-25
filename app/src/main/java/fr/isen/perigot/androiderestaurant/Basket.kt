package fr.isen.perigot.androiderestaurant

import android.content.ClipData
import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File
import java.io.Serializable


const val BASKET_FILE = "basket.json"

class Basket(val items: MutableList<ClipData.Item>): Serializable {

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
