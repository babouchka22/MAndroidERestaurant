package fr.isen.perigot.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.modele.Items

class ViewPagerAdapter(private var dishes: List<Items>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.activity_description_dish, container, false)
        val dishImage = view.findViewById<ImageView>(R.id.dishPicture)

        if (dishes[position].images[0].isNotEmpty()) {
            Picasso.get()
                .load(dishes[position].images[0])
                .placeholder(R.drawable.salade_lyonnaise)
                .into(dishImage)
        }

        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return dishes.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }


}