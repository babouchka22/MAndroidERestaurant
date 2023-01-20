package fr.isen.perigot.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.modele.Items

class DishAdapter(private var dishes: List<Items>, val onItemClickListener: (Items) -> Unit) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {


    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishTitle: TextView = itemView.findViewById(R.id.dishTitle)
        val dishImage: ImageView = itemView.findViewById(R.id.dishImage)
        val sellPrice: TextView = itemView.findViewById(R.id.sellPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishAdapter.DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishAdapter.DishViewHolder(view)
    }


    override fun onBindViewHolder(holder: DishAdapter.DishViewHolder, position: Int) {

        holder.sellPrice.text = dishes[position].prices[0].price
        holder.dishTitle.text = dishes[position].nameFr
        holder.itemView.setOnClickListener {
            onItemClickListener(dishes[position])
        }

        if (dishes[position].images[0].isNotEmpty()) {
            Picasso.get()
                .load(dishes[position].images[0])
                .placeholder(R.drawable.salade_lyonnaise)
                .into(holder.dishImage)
        }


    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun refreshList(dishesfromApi: ArrayList<Items>) {
        dishes = dishesfromApi
        notifyDataSetChanged()
    }
}
