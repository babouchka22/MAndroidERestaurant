package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.isen.perigot.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.perigot.androiderestaurant.modele.Items

class BasketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBasketBinding
   // private lateinit var item: BasketItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // item = intent.getSerializableExtra("dish") as BasketItem
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_basket)


        loadList()

        binding.passercommande.setOnClickListener {
            this.cacheDir.absolutePath
            Snackbar.make(binding.root, "Commande effectuée", Snackbar.LENGTH_SHORT).show()
        }

       // binding.totalPrice.text = String.format("Total %.2f €", (item.dish.prices[0].price?.toDouble() ?: 0.0) * item.quantity)

    }



    private fun loadList() {
        val basket = Basket.getBasket(this)
        val items = basket.items
        binding.recyclerViewBasket.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewBasket.adapter = BasketAdapter(items) {
            basket.removeDish(it)
            basket.save(this)
            loadList()
        }
    }


}