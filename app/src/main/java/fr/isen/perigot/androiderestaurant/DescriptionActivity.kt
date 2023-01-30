package fr.isen.perigot.androiderestaurant

//import android.widget.Toolbar
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import fr.isen.perigot.androiderestaurant.databinding.ActivityDescriptionDishBinding
import fr.isen.perigot.androiderestaurant.modele.Items

@Suppress("DEPRECATION")
class DescriptionActivity : AppCompatActivity() {

    private lateinit var food: String
    private lateinit var item: Items
    private lateinit var binding: ActivityDescriptionDishBinding
    private var dishQuantity = 1
    private var quantityEnregistre = 0
    //private lateinit var cartBadge: TextView
    private var cartItemCount: Int = 0

    private lateinit var viewPager: ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private val SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO"
    private val SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_description_dish)
        item = intent.getSerializableExtra("dish") as Items

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

        // lecture de la quantité
        quantityEnregistre = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(
            SHARED_PREF_USER_INFO_NAME,
            0
        )


        binding.dishIngredient.text = ingredientString


        binding.TotalPrice.text = item.prices.joinToString("\n") { "Total " + it.price + "€" }


        binding.boutonMoins.setOnClickListener{
            if ( dishQuantity == 1)
            {
                dishQuantity = 1
            }
            else {
                dishQuantity -= 1
                binding.dishQuantity.text = dishQuantity.toString()
                binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)
               // updateCartBadge()
            }

        }

        binding.boutonPlus.setOnClickListener {
            dishQuantity += 1
            binding.dishQuantity.text = dishQuantity.toString()
            binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)
            //updateCartBadge()
        }


        binding.TotalPrice.setOnClickListener {
            // Ajouter au panier
            this.cacheDir.absolutePath
            val basket = Basket.getBasket(this)
            basket.addItem(item, dishQuantity)
            basket.save(this)
            Snackbar.make(binding.root, "Bien ajouté au panier", Snackbar.LENGTH_SHORT).show()

            // ecriture de la pastille
            val nbEnregistre = quantityEnregistre + 1
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .edit()
                .putInt(SHARED_PREF_USER_INFO_NAME, nbEnregistre)
                .apply();

            invalidateOptionsMenu()
        }

        //Code pour le carrousel
        viewPager = findViewById(R.id.viewPager)
        mViewPagerAdapter = ViewPagerAdapter(this, item.images)
        viewPager.pageMargin = 15
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setClipToPadding(false)
        viewPager.setPageMargin(25)
        viewPager.adapter = mViewPagerAdapter

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.mon_menu, menu)


        val menuItem = menu?.findItem(R.id.menu_item_image)?.actionView

        // lecture de la quantité
        val quantityPreference = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(
            SHARED_PREF_USER_INFO_NAME,
            0
        )
        // mettre à jour la pastille
        val cartBadge: TextView? = menuItem?.findViewById(R.id.cart_badge)
        cartBadge?.text = quantityPreference.toString()


        menuItem?.setOnClickListener {
            Toast.makeText(this, "Click Chariot", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@DescriptionActivity, BasketActivity::class.java)
            startActivity(intent);
        }

        return true

/*
        menuInflater.inflate(R.menu.mon_menu,menu)
        val badge = findViewById<TextView>(R.id.cart_badge)
        badge.text = "2"
        badge.visibility = View.VISIBLE
        return true*/


/*
        menuInflater.inflate(R.menu.mon_menu, menu)
        val cartItem = menu?.findItem(R.id.menu_item_image)
        val cartActionView = cartItem?.actionView
        cartBadgeContainer = cartActionView?.findViewById(R.id.cart_badge_container)!!
        cartBadge = cartBadgeContainer?.findViewById(R.id.cart_badge)!!
        updateCartBadge()
        return true*/

    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // code pour gérer l'action de l'élément de menu
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/
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
/*
//met a jour la pastille
    private fun updateCartBadge() {
        if (cartItemCount == 0) {
            cartBadge.visibility = View.GONE
        } else {
            cartBadge.visibility = View.VISIBLE
            cartBadge.text = cartItemCount.toString()
        }
    }*/



}