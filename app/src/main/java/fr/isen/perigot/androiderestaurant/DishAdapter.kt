package fr.isen.perigot.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val dishes: List<String>) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishAdapter.DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishAdapter.DishViewHolder(view)
    }


    override fun onBindViewHolder(holder: DishAdapter.DishViewHolder, position: Int) {
       // val dishes: Dishes = dishes[position]
       // val textView = viewHolder.nameTextView
       // textView.text = dish.name
        holder.bind(dishes[position])
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dishTitle: TextView = itemView.findViewById(R.id.dishTitle)
        fun bind(dish: String) {
            dishTitle.text = dish
        }
    }

}