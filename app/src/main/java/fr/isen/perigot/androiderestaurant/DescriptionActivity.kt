package fr.isen.perigot.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.databinding.ActivityDescriptionDishBinding
import fr.isen.perigot.androiderestaurant.modele.Items

@Suppress("DEPRECATION")
class DescriptionActivity : AppCompatActivity() {

    private lateinit var food: String
    private lateinit var item: Items
    private lateinit var binding: ActivityDescriptionDishBinding
    private var dishQuantity = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_description_dish)
        //binding.dishQuantity = dishQuantity

        //binding.dishQuantity = dishQuantity

        setContentView(R.layout.activity_description_dish)
        item = intent.getSerializableExtra("dish") as Items
        food = intent.getStringExtra("dish") ?: ""

        binding = ActivityDescriptionDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        food = item.nameFr.toString()


        val actionBar = supportActionBar
        actionBar?.title = food

/*
        val imageView = ImageView(this)
        Glide.with(this)
            .load(R.drawable.basket)
            .override(48, 48)
            .into(imageView)
        supportActionBar?.setHomeAsUpIndicator(imageView.drawable)
*/
//afficher l'image chariot dans la toolbar
/*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.basket)*/

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


        val boutonMoins = findViewById<Button>(R.id.boutonMoins)

        binding.boutonMoins.setOnClickListener{
            if ( dishQuantity == 1)
            {
                dishQuantity = 1
            }
            else {
                dishQuantity -= 1
                binding.dishQuantity.text = dishQuantity.toString()
                binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)
            }

        }

        binding.boutonPlus.setOnClickListener {
            dishQuantity += 1
            binding.dishQuantity.text = dishQuantity.toString()
            binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)

        }


        binding.TotalPrice.setOnClickListener {
            // Add to basket
            this.cacheDir.absolutePath
            val basket = Basket.getBasket(this)
            Snackbar.make(binding.root, "Bien ajouté au panier", Snackbar.LENGTH_SHORT).show()
        }

/*
        val toolbar = findViewById<Toolbar>(R.id.action_settings)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.chariot)
        toolbar.inflateMenu(R.menu.main_menu)//main_menu
*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.mon_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // code pour gérer l'action de l'élément de menu
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
/*
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        when (item?.itemId)
        {
            R.id.item_favoris ->
            {
                if (isFavori == false)
                {
                    item.setIcon(R.drawable.btn_rating_star_off_pressed)
                    Toast.makeText(this,"Favoris",Toast.LENGTH_LONG).show()
                    isFavori = true
                }
                else
                {
                    item.setIcon(R.drawable.btn_rating_star_off_normal)
                    isFavori = false
                }
            }
            R.id.configuration -> { Toast.makeText(this,"Paramètres",Toast.LENGTH_LONG).show() }
            R.id.Effacer  ->
            {
                mon_editText.text.clear()
                Toast.makeText(this,"le contenu a été effacé",Toast.LENGTH_LONG).show()
            }
            else -> { Toast.makeText(this,"action inconnu",Toast.LENGTH_LONG).show() }
        }
        return true
    }
*/
}