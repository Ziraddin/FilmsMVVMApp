package com.zireddinismayilov.examplemvvmapp.utils.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zireddinismayilov.examplemvvmapp.R
import com.zireddinismayilov.examplemvvmapp.data.response.MovieItem
import kotlin.random.Random

class MoviesAdapter(private var MovieList: MutableList<MovieItem>, val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val randomInt = Random.nextInt(0, 3)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTV)
        val year: TextView = itemView.findViewById(R.id.yearTV)
        val length: TextView = itemView.findViewById(R.id.runtime)
        val posterImage: ImageView = itemView.findViewById(R.id.posterIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return MovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.item_anim)
        holder.title.text = MovieList[position].Title
        holder.year.text = "Year : " + MovieList[position].Year
        holder.length.text = "Duration : " + MovieList[position].Runtime
        Picasso.get().load(MovieList[position].Images[randomInt]).into(holder.posterImage)
    }
}