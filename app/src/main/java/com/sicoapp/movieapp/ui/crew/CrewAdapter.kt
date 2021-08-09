package com.sicoapp.movieapp.ui.crew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemCreditsBinding

class CrewAdapter: RecyclerView.Adapter<CrewAdapter.CrewViewHolder>(){

    var list = mutableListOf<CrewObservable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
       val binding :ItemCreditsBinding = DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.item_credits,
           parent,
           false
       )
        return CrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    class CrewViewHolder(val binding: ItemCreditsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.data, obj)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = list.size

    fun addCast(crewItems: List<CrewObservable>?) {
        list.addAll(crewItems!!)
        notifyDataSetChanged()
    }
}