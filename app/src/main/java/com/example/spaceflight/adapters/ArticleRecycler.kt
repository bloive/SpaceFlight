package com.example.spaceflight.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflight.databinding.ViewPagerBinding
import com.example.spaceflight.extensions.collapse
import com.example.spaceflight.extensions.expand
import com.example.spaceflight.extensions.fromUrl
import com.example.spaceflight.models.ArticleItem

class ArticleRecycler : RecyclerView.Adapter<ArticleRecycler.ArticleViewHolder>() {

    private val items = mutableListOf<ArticleItem>()

    inner class ArticleViewHolder(private val binding: ViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: ArticleItem
        fun bind() {
            item = items[adapterPosition]
            with(binding) {
                image.fromUrl(item.imageUrl.toString())
                tvDescriptContent.text = item.summary
                if (item.events!!.isNotEmpty()) {
                    tvTimnelineContent.text = item.events.toString()
                }
                tvTitle.text = item.title.toString()
                tvUpdatedAt.text = item.publishedAt.toString()
                with(tvDescriptContent) {
                    btnDescript.setOnClickListener {
                        if (isVisible) {
                            visibility = View.VISIBLE
                            collapse(btnDescript)
                        } else {
                            tvDescriptContent.visibility = View.GONE
                            tvDescriptContent.expand(btnDescript)
                        }
                    }
                }

                with(tvTimnelineContent) {
                    btnTimeline.setOnClickListener {
                        if (isVisible) {
                            visibility = View.VISIBLE
                            collapse(btnTimeline)
                        } else {
                            visibility = View.GONE
                            expand(btnTimeline)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleView =
            ViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    fun setData(items: MutableList<ArticleItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}