package com.example.spaceflight.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflight.R
import com.example.spaceflight.databinding.DrawerItemBinding
import com.example.spaceflight.ui.MenuItemModel

typealias MenuItemOnClick = (position: Int) -> Unit

class DrawerAdapter(private val items: MutableList<MenuItemModel>) :
    RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

    lateinit var menuItemOnClick: MenuItemOnClick

    var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val drawerView =
            DrawerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrawerViewHolder(drawerView)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = items.size

    inner class DrawerViewHolder(private val binding: DrawerItemBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private lateinit var model: MenuItemModel
        fun onBind() {
            model = items[adapterPosition]
            with(binding) {
                image.setImageResource(model.image)
                text.text = model.title
            }
            binding.root.setOnClickListener(this)
            if (adapterPosition == selectedPosition) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.selected_drawer_item
                    )
                )
                binding.indicator.visibility = View.VISIBLE
            } else {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )
                binding.indicator.visibility = View.INVISIBLE
            }
        }

        override fun onClick(v: View?) {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
//            menuItemOnClick.invoke(adapterPosition)
        }

    }
}