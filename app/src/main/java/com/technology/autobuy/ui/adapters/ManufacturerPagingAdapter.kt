package com.technology.autobuy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.databinding.LayoutItemListBinding
import com.technology.autobuy.utils.interfaces.ItemClickListener
import javax.inject.Inject

class ManufacturerPagingAdapter @Inject constructor() :
    PagingDataAdapter<ManufacturerItem, ManufacturerPagingAdapter.ViewHolder>(ManufactureComparator) {
    var manufacturerClickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    inner class ViewHolder(private val binding: LayoutItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                manufacturerClickListener?.onClick(
                    getItem(absoluteAdapterPosition),
                    absoluteAdapterPosition
                )
            }
        }

        fun bind(item: ManufacturerItem) = with(binding) {
            tvName.text = item.name
        }
    }

    object ManufactureComparator : DiffUtil.ItemCallback<ManufacturerItem>() {
        override fun areItemsTheSame(oldItem: ManufacturerItem, newItem: ManufacturerItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ManufacturerItem, newItem: ManufacturerItem) =
            oldItem == newItem
    }
}