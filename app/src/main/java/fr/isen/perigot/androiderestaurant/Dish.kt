package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Dish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

/*
        val queue = Volley.newRequestQueue(context)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
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

                    }
                }

            },
            Response.ErrorListener { error ->
                // code pour traiter les erreurs ici
            })
        queue.add(jsonRequest)

        */
    }
}