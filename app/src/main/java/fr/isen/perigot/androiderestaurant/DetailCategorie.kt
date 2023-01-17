package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailCategorie : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_categorie)
        val categoryName = intent.getStringExtra("categoryName")
        this.title = categoryName

        //val categoryName = intent.getStringExtra("category_name")
       // val dishTitles = resources.getStringArray(R.array.dish_entrees)
        //val dishes = dishTitles.filter { it.contains(categoryName) }

       // val adapter = DishAdapter(dishTitles.toList())

       // val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

      //  dishes.adapter = adapter

      //  dishes.layoutManager = LinearLayoutManager(this)



        if (categoryName.equals("Entrees")){

            val arrayEntrees : kotlin.Array<kotlin.String> = resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_entrees)

            val dishTitles = resources.getStringArray(R.array.dish_entrees)

            val adapter = DishAdapter(dishTitles.toList())

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

            dishes.adapter = adapter

            dishes.layoutManager = LinearLayoutManager(this)
        }

        if (categoryName.equals("Plats")){

            val arrayEntrees : kotlin.Array<kotlin.String> = resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_plats)

            val dishTitles = resources.getStringArray(R.array.dish_plats)

            val adapter = DishAdapter(dishTitles.toList())

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

            dishes.adapter = adapter

            dishes.layoutManager = LinearLayoutManager(this)
        }

        if (categoryName.equals("Desserts")){

            val arrayEntrees : kotlin.Array<kotlin.String> = resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_desserts)

            val dishTitles = resources.getStringArray(R.array.dish_desserts)

            val adapter = DishAdapter(dishTitles.toList())

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

            dishes.adapter = adapter

            dishes.layoutManager = LinearLayoutManager(this)
        }


    }
}