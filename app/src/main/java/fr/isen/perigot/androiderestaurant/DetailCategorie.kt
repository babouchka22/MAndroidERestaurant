package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val dishTitles = resources.getStringArray(R.array.dish_titles)
        //val dishes = dishTitles.filter { it.contains(categoryName) }


    }
}