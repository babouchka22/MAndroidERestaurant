package fr.isen.perigot.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val dishes: List<String>, val onItemClickListener: () -> Unit) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {


    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishTitle: TextView = itemView.findViewById(R.id.dishTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishAdapter.DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishAdapter.DishViewHolder(view)
    }


    override fun onBindViewHolder(holder: DishAdapter.DishViewHolder, position: Int) {

        holder.dishTitle.text = dishes[position]
        holder.itemView.setOnClickListener {
            onItemClickListener()
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

}
