package fr.isen.perigot.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
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

    //private lateinit var cartBadge: TextView
    private var cartItemCount: Int = 0

    private lateinit var viewPager: ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    @SuppressLint("MissingInflatedId")
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


        binding.boutonMoins.setOnClickListener{
            if ( dishQuantity == 1)
            {
                dishQuantity = 1
            }
            else {
                dishQuantity -= 1
                binding.dishQuantity.text = dishQuantity.toString()
                binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)
                updateCartBadge()
            }

        }

        binding.boutonPlus.setOnClickListener {
            dishQuantity += 1
            binding.dishQuantity.text = dishQuantity.toString()
            binding.TotalPrice.text = String.format("Total %.2f €", (item.prices[0].price?.toDouble() ?: 0.0) * dishQuantity)
            updateCartBadge()
        }


        binding.TotalPrice.setOnClickListener {
            // Ajouter au panier
            this.cacheDir.absolutePath
            val basket = Basket.getBasket(this)
            basket.addItem(item, dishQuantity)
            basket.save(this)
            Snackbar.make(binding.root, "Bien ajouté au panier", Snackbar.LENGTH_SHORT).show()
            invalidateOptionsMenu()
        }
/*
        //test pour carrousel
        //var dishes = List<Items>()
        viewPager = findViewById(R.id.viewPager)
        //mViewPagerAdapter = ViewPagerAdapter(dishes)
        viewPager.pageMargin = 15
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setClipToPadding(false)
        viewPager.setPageMargin(25)
        viewPager.adapter = mViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)*/

        //binding.viewPager.adapter = DishPictureAdapter(this, item.images)
/*
        //val dishes = mutableListOf<Items>()
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(item.images.)
        viewPager.adapter = adapter*/

        // ces deux lignes fond sauter la page pour accéder à la description des plats
        //val cartBadge: TextView = findViewById(R.id.cart_badge)
        //cartBadge.text = cartItemCount.toString()
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                // your logic here
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // your logic here
            }

            override fun onPageSelected(position: Int) {
                // your logic here
            }
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.mon_menu,menu)

        val menuItem = menu?.findItem(R.id.menu_item_image)
        val actionView = menuItem?.getActionView()


        if (actionView != null) {
            actionView.setOnClickListener {
                Toast.makeText(this,"Click Chariot", Toast.LENGTH_SHORT).show();

                val intent = Intent(this@DescriptionActivity, BasketActivity::class.java)
                startActivity(intent);
            }
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

    //Fonction pour mettre à jour la valeur de la TextView
    private fun updateCartBadge() {
        val cartBadge: TextView = findViewById(R.id.cart_badge)
        cartBadge.text = cartItemCount.toString()
    }

    //Fonction pour ajouter un article au panier
    fun addToCart() {
        cartItemCount++
        updateCartBadge()
    }

    //Fonction pour supprimer un article du panier
    fun removeFromCart() {
        if (cartItemCount > 0) {
            cartItemCount--
            updateCartBadge()
        }
    }

}