package com.technology.autobuy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technology.autobuy.R
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.databinding.LayoutItemListBinding
import com.technology.autobuy.utils.interfaces.ItemClickListener
import com.technology.autobuy.utils.isNightTheme
import javax.inject.Inject

class ListAdapter @Inject constructor() :
    RecyclerView.Adapter<ListAdapter.CarTypeViewHolder>() {
    var itemClickListener: ItemClickListener? = null
    private var list: MutableList<ManufacturerItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarTypeViewHolder {
        return CarTypeViewHolder(
            LayoutItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarTypeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<ManufacturerItem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class CarTypeViewHolder(private val layoutItemManufacturerBinding: LayoutItemListBinding) :
        RecyclerView.ViewHolder(layoutItemManufacturerBinding.root) {
        init {
            itemView.setOnClickListener {
                itemClickListener?.onClick(
                    list[absoluteAdapterPosition],
                    absoluteAdapterPosition
                )
            }
        }

        fun bind(item: ManufacturerItem) {
            with(layoutItemManufacturerBinding) {
                tvName.text = item.name
                val evenColor =
                    if (tvName.context.isNightTheme()) R.color.material_dynamic_secondary20 else R.color.material_dynamic_secondary95
                val oddColor =
                    if (tvName.context.isNightTheme()) R.color.material_dynamic_primary20 else R.color.material_dynamic_primary95

                if (absoluteAdapterPosition % 2 == 0)
                    root.setCardBackgroundColor(root.context.getColor(evenColor))
                else
                    root.setCardBackgroundColor(root.context.getColor(oddColor))
            }
        }
    }
}


