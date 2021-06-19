package com.example.geckoweatherapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.geckoweatherapp.R
import com.example.geckoweatherapp.databinding.ItemBookmarkBinding

class HomeAdapter :
    ListAdapter<Bookmark, HomeAdapter.BookmarkHolder>(object : DiffUtil.ItemCallback<Bookmark>() {
        override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark, parent, false)
        val binding = ItemBookmarkBinding.bind(v)
        return BookmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookmarkHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bookmark: Bookmark) = with(binding) {
            nameTextView.text = bookmark.name
            root.setOnClickListener {
                it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToCityFragment(bookmark))
            }
        }

    }
}