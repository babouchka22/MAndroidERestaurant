package fr.isen.perigot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.perigot.androiderestaurant.databinding.ActivityDetailCategorieBinding
import org.json.JSONObject
import com.google.gson.Gson
import fr.isen.perigot.androiderestaurant.modele.DataResult
import fr.isen.perigot.androiderestaurant.modele.Items


class CategoryActivity : AppCompatActivity() {

    private lateinit var category: String
    private lateinit var binding: ActivityDetailCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        category = intent.getStringExtra("categoryName") ?: ""
        this.title = category

        binding = ActivityDetailCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryList.layoutManager = LinearLayoutManager(this)


        val dishTitles = when (category) {
            "Entrées" -> resources.getStringArray(R.array.dish_entrees).toList()
            "Plats" -> resources.getStringArray(R.array.dish_plats).toList()
            "Desserts" -> resources.getStringArray(R.array.dish_desserts).toList()
            else -> arrayListOf("")
        }

        //dishes.adapter = DishAdapter(dishTitles.toList())

        binding.categoryList.adapter = DishAdapter(arrayListOf()) {
            val intent = Intent(this@CategoryActivity, DescriptionActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }

        loadDishesFromAPI()
    }

    private fun loadDishesFromAPI() {
        //val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, {
                Log.w("CategoryActivity", "response : $it")
                handleAPIData(it.toString())
            },
            {
                Log.e("CategoryActivity", "erreur : $it")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        val gson = Gson()
        var dishesResult = gson.fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }

        val adapter = binding.categoryList.adapter as DishAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items>)
    }



}