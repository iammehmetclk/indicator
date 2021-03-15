package com.android.playground

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.playground.databinding.ListItemFilterBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var selectedPosition = 0
    var list = listOf(
        ListItem(true, "All Items"),
        ListItem(false, "Images"),
        ListItem(false, "Videos"),
        ListItem(false, "Food"),
        ListItem(false, "Rooms"),
        ListItem(false, "Pools"),
        ListItem(false, "Sea Side"),
        ListItem(false, "Beach"),
    )
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ListItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem, position: Int) {
            binding.title.text = item.title
            if (item.isSelected) {
                binding.indicator.visibility = View.VISIBLE
                binding.title.background = ContextCompat.getDrawable(binding.root.context, R.drawable.bg_filter_active)
            }
            else {
                binding.indicator.visibility = View.INVISIBLE
                binding.title.background = ContextCompat.getDrawable(binding.root.context, R.drawable.bg_filter_inactive)
            }

            binding.root.setOnClickListener {
                list[selectedPosition].isSelected = false
                list[position].isSelected = true
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val binding =
            ListItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}