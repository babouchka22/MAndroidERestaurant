package fr.isen.perigot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DishPictureAdapter(activity: AppCompatActivity, private val images: List<String>):
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        return DishPictureFragment.newInstance(images[position])
    }

}