package fr.isen.perigot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoryActivity : AppCompatActivity() {

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

            val adapter = DishAdapter(dishTitles.toList()) {
                val intent = Intent(this@CategoryActivity, CategoryActivity::class.java)
                intent.putExtra("categoryName", "Entree")
                startActivity(intent);
            }

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

            dishes.adapter = adapter

            dishes.layoutManager = LinearLayoutManager(this)

        }

       if (categoryName.equals("Plats")){

            val arrayEntrees : kotlin.Array<kotlin.String> = resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_plats)

            val dishTitles = resources.getStringArray(R.array.dish_plats)

            val adapter = DishAdapter(dishTitles.toList()) {
                val intent = Intent(this@CategoryActivity, CategoryActivity::class.java)
                intent.putExtra("categoryName", "Plats")
                startActivity(intent);
            }

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView

            dishes.adapter = adapter

            dishes.layoutManager = LinearLayoutManager(this)
        }

        if (categoryName.equals("Desserts")){

            val arrayEntrees : kotlin.Array<kotlin.String> = resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_desserts)

            val dishTitles = resources.getStringArray(R.array.dish_desserts)

            //val adapter =

            val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView


        }

        /*
        val dishTitles = when (categoryName) {
            "entrees" -> resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_entrees)
            else -> arrayListOf()
            //"plats" -> resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_plats)
           // else -> arrayListOf()
           // "desserts" -> resources.getStringArray(fr.isen.perigot.androiderestaurant.R.array.dish_desserts)
          //  else -> arrayListOf()
        }

        //dishes.adapter = DishAdapter(dishTitles.toList())

        dishTitles.adapter = DishAdapter(dishTitles.toList()) {
            val intent = Intent(this@CategoryActivity, CategoryActivity::class.java)
            intent.putExtra("categoryName", "Entree")
            startActivity(intent);
        }

        dishTitles.layoutManager = LinearLayoutManager(this)

*/
    }
}