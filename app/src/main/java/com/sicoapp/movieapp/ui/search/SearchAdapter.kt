package com.sicoapp.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import javax.inject.Inject

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchItems = mutableListOf<BindMulti>()
    private var onClickListener: OnClickListener? = null
    lateinit var binding : ItemMovieSearchBinding
    lateinit var mediaTyp: String
    lateinit var listenerCall: ListenerCall

    interface ListenerCall {
        fun callback(binding: ItemMovieSearchBinding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemMovieSearchBinding.inflate(
            layoutInflater, parent, false
        )
        listenerCall.callback(binding)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)

    }

    class SearchViewHolder(val binding: ItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Any?) {
            binding.setVariable(BR.data, obj)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = searchItems.size

    fun updateItems(newList: List<BindMulti>) {
        searchItems.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        searchItems.clear()
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun openDetails(movieId: Long, mediaTyp: String)
    }
}




