package fr.isen.perigot.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.perigot.androiderestaurant.databinding.DishPictureFragmentBinding

class DishPictureFragment : Fragment() {

    private lateinit var binding: DishPictureFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DishPictureFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("picture_url")?.let { pictureUrl ->
            if(pictureUrl.isNotEmpty()){
                Picasso.get()
                    .load(pictureUrl)
                    .placeholder(R.drawable.salade_lyonnaise)
                    .into(binding.dishPictureFrag)
            }else {
                binding.dishPictureFrag.setImageResource(R.drawable.salade_lyonnaise)
            }
        }
    }

    companion object {
        fun newInstance(pictureUrl: String) =
            DishPictureFragment().apply {
                arguments = Bundle().apply {
                    putString("picture_url", pictureUrl)
                }
            }

    }

}
