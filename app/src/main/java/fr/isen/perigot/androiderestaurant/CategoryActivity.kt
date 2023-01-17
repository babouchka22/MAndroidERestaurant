package fr.isen.perigot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.perigot.androiderestaurant.databinding.ActivityDetailCategorieBinding
import fr.isen.perigot.androiderestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject


class CategoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityDetailCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_categorie)

        val categoryName = intent.getStringExtra("categoryName")
        this.title = categoryName

        binding = ActivityDetailCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val categoryName = intent.getStringExtra("category_name")
       // val dishTitles = resources.getStringArray(R.array.dish_entrees)
        //val dishes = dishTitles.filter { it.contains(categoryName) }

       // val adapter = DishAdapter(dishTitles.toList())

        val dishes =findViewById<View>(R.id.recyclerView) as RecyclerView



        dishes.layoutManager = LinearLayoutManager(this)





        val dishTitles = when (categoryName) {
            "Entrees" -> resources.getStringArray(R.array.dish_entrees).toList()
            "Plats" -> resources.getStringArray(R.array.dish_plats).toList()
            "Desserts" -> resources.getStringArray(R.array.dish_desserts).toList()
            else -> arrayListOf("")
        }

        //dishes.adapter = DishAdapter(dishTitles.toList())

        dishes.adapter = DishAdapter(dishTitles) {
            val intent = Intent(this@CategoryActivity, CategoryActivity::class.java)
            intent.putExtra("categoryName", it)
            startActivity(intent);
        }


        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                // récupération de la liste des entrées, plats et desserts
                val entries = response.getJSONArray("entries")
                val mainCourses = response.getJSONArray("mainCourses")
                val desserts = response.getJSONArray("desserts")
                // boucle pour parcourir les entrées
                for (i in 0 until entries.length()) {
                    val entry = entries.getJSONObject(i)
                    val entryName = entry.getString("name")
                    // faire quelque chose avec entryName
                }
                // boucle pour parcourir les plats
                for (i in 0 until mainCourses.length()) {
                    val mainCourse = mainCourses.getJSONObject(i)
                    val mainCourseName = mainCourse.getString("name")
                    // faire quelque chose avec mainCourseName
                }
                // boucle pour parcourir les desserts
                for (i in 0 until desserts.length()) {
                    val dessert = desserts.getJSONObject(i)
                    val dessertName = dessert.getString("name")
                    // faire quelque chose avec dessertName
                }
            },
            Response.ErrorListener { error ->
                // code pour traiter les erreurs ici
            })
        queue.add(jsonRequest)

    }
}