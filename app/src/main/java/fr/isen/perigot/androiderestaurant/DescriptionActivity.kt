package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.databinding.ActivityDescriptionDishBinding
import fr.isen.perigot.androiderestaurant.databinding.ActivityDetailCategorieBinding
import fr.isen.perigot.androiderestaurant.modele.Items

@Suppress("DEPRECATION")
class DescriptionActivity : AppCompatActivity() {

    private lateinit var food: String
    private lateinit var item: Items
    private lateinit var binding: ActivityDescriptionDishBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_description_dish)
        item = intent.getSerializableExtra("dish") as Items
        food = intent.getStringExtra("dish") ?: ""

        binding = ActivityDescriptionDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        food = item.nameFr.toString()


        val actionBar = supportActionBar
        actionBar?.title = food


        binding.dishname.text = item.nameFr

        val ingredients = item.ingredients
        val ingredientString = StringBuilder()
        ingredients.forEach { ingredient ->
            ingredientString.append(ingredient.nameFr)
            ingredientString.append("\n")
        }
        binding.dishIngredient.text = ingredientString


        if (item.images[0].isNotEmpty()) {
            Picasso.get()
                .load(item.images[0])
                .placeholder(R.drawable.salade_lyonnaise)
                .into(binding.dishPicture)
        }

        binding.TotalPrice.text = item.prices.joinToString("\n") { "Total " + it.price + "€" }

        /*
        binding.boutonMoins.setOnClickListener{
            if ( dishQuantity == 1)
            {
                dishQuantity = 1
            }
            else {
                itemCount -= 1
            }

        }

        binding.boutonPlus.setOnClickListener {
            itemCount += 1

        }
        */

    }


}