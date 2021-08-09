package com.sicoapp.movieapp.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemMoviePopularBinding
import javax.inject.Inject

class PopularMovieAdapter @Inject constructor() : RecyclerView.Adapter<PopularMovieAdapter.PopularViewHolder>() {

    var list = mutableListOf<BindMovie>()
    lateinit var binding: ItemMoviePopularBinding
    lateinit var listenerCall: ListenerCall

    interface ListenerCall {
        fun callback(binding: ItemMoviePopularBinding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PopularViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_popular,
            parent,
            false
        )
        listenerCall.callback(binding)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = list.size

    class PopularViewHolder(val binding: ItemMoviePopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.data, obj)
            binding.executePendingBindings()
        }
    }

    fun addMovies(listItems: List<BindMovie>) {
        list.addAll(listItems)
        notifyDataSetChanged()
    }
}