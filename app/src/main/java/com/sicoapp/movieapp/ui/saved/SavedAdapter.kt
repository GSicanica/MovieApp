package com.sicoapp.movieapp.ui.saved

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.database.Rating
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.utils.URL_IMAGE

class SavedAdapter : RecyclerView.Adapter<SavedAdapter.ViewHolderSaved>() {

    var listSaved = listOf<Rating>()
    var movieList = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSaved {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_saved, parent, false)
        return ViewHolderSaved(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolderSaved, position: Int) {

        val dataModel = listSaved[position]
        val dataMovie = movieList[position]

        holder.contentView.text = dataModel.rating.toString()

        Glide.with(holder.idView.rootView.context)
            .load(URL_IMAGE + dataMovie.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.idView)
    }

    fun addMovieWithRating(allElement: List<Movie>, list: List<Rating>) {
        movieList = allElement
        listSaved = list
        notifyDataSetChanged()
    }

    class ViewHolderSaved(view: View) : RecyclerView.ViewHolder(view) {
        var idView: ImageView = view.findViewById(R.id.item_image)
        val contentView: TextView = view.findViewById(R.id.content)
    }
}