package fr.isen.perigot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.perigot.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrees.setOnClickListener {
            Toast.makeText(this,"Click Entree", Toast.LENGTH_SHORT).show();

            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("categoryName", "Entrées")
            startActivity(intent);
        }

        binding.plats.setOnClickListener {
            Toast.makeText(this,"Click Plat", Toast.LENGTH_SHORT).show();

            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("categoryName", "Plats")
            startActivity(intent);
        }

        binding.desserts.setOnClickListener {
            Toast.makeText(this, "Click Dessert", Toast.LENGTH_SHORT).show();

            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("categoryName", "Desserts")
            startActivity(intent);
        }
    }
/*
    fun ClickDessert(view: View) {
        Toast.makeText(this,"Click Dessert", Toast.LENGTH_SHORT).show();

        val intent = Intent(this@HomeActivity, DetailCategorie::class.java)
        intent.putExtra("categoryName", "Desserts")
        startActivity(intent);
    }

 */
/*
    fun ClickEntrée(view: View) {
        Toast.makeText(this,"Click Entree", Toast.LENGTH_SHORT).show();

        val intent = Intent(this@HomeActivity, DetailCategorie::class.java)
        intent.putExtra("categoryName", "Entrees")
        startActivity(intent);
    }
*/
/*
    fun ClickPlat(view: View) {
        Toast.makeText(this,"Click Plat", Toast.LENGTH_SHORT).show();

        val intent = Intent(this@HomeActivity, DetailCategorie::class.java)
        intent.putExtra("categoryName", "Plats")
        startActivity(intent);
    }
*/
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "L'activité Home a été détruite")
    }

}

