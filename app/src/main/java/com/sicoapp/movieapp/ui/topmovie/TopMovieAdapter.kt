package com.sicoapp.movieapp.ui.topmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemMovieTopBinding
import com.sicoapp.movieapp.ui.popular.BindMovie
import javax.inject.Inject

class TopMovieAdapter @Inject constructor(): RecyclerView.Adapter<TopMovieAdapter.TopViewHolder>() {

    var list = mutableListOf<BindMovie>()
    lateinit var binding: ItemMovieTopBinding
    lateinit var listenerCall: ListenerCall

    interface ListenerCall {
        fun callback(binding: ItemMovieTopBinding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TopViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_top,
            parent,
            false
        )
        listenerCall.callback(binding)
        return TopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)
    }


    override fun getItemCount() = list.size

    fun addMovies(listItems: List<BindMovie>) {
        list.addAll(listItems)
        notifyDataSetChanged()
    }

    class TopViewHolder(val binding: ItemMovieTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.data, obj)
            binding.executePendingBindings()
        }
    }
}