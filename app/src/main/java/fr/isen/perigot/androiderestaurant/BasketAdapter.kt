package fr.isen.perigot.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.modele.Items
import fr.isen.perigot.androiderestaurant.databinding.CelluleBasketBinding


class BasketAdapter(private val items: List<BasketItem>,
    val deleteDishClickListener: (BasketItem) -> Unit)
    : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>()  {

    class BasketViewHolder(binding: CelluleBasketBinding) : RecyclerView.ViewHolder(binding.root) {
        val dishName: TextView = binding.dishname
        val dishPrice: TextView = binding.unityprice
        val dishQuantity: TextView = binding.dishquantity
        val delete: ImageView = binding.deleteDish
        val dishPicture: ImageView = binding.dishPicture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(CelluleBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }


    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem = items[position]
        holder.dishName.text = basketItem.dish.nameFr
        holder.dishQuantity.text = "Quantity : " + basketItem.quantity.toString()
        holder.dishPrice.text = basketItem.dish.prices.first().price + " € "
        holder.dishPrice.text = String.format("Total %.2f €", (basketItem.dish.prices[0].price?.toDouble() ?: 0.0) * basketItem.quantity)

        holder.delete.setOnClickListener{
            deleteDishClickListener.invoke(basketItem)
        }


        if (basketItem.dish.images[0].isNotEmpty()) {
            Picasso.get()
                .load(basketItem.dish.images[0])
                .placeholder(R.drawable.salade_lyonnaise)
                .into(holder.dishPicture)
        }

    }


    override fun getItemCount(): Int {
        return items.count()
    }

}
