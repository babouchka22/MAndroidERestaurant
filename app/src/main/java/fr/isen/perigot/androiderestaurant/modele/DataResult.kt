package fr.isen.perigot.androiderestaurant.modele

import com.google.gson.annotations.SerializedName


data class DataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

) : java.io.Serializable