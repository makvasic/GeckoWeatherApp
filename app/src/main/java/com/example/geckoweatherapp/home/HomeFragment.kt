package com.example.geckoweatherapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.geckoweatherapp.databinding.FragmentHomeBinding
import com.example.geckoweatherapp.main.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val adapter: HomeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter
        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val bookmark = adapter.currentList[viewHolder.adapterPosition]
                    mainViewModel.deleteBookmark(bookmark)
                }
            }).attachToRecyclerView(binding.recyclerView)


        binding.fab.setOnClickListener {
            mainViewModel.insertBookmarks(
                listOf(
                    Bookmark(1, "Beograd", 44.80401f, 20.46513f),
                    Bookmark(2, "Kragujevac", 44.01667f, 20.91667f),
                    Bookmark(3, "Čačak", 43.89139f, 20.34972f),
                    Bookmark(4, "Novi Sad", 45.25167f, 19.83694f),
                    Bookmark(5, "Niš", 43.32472f, 21.90333f),
                )
            )
        }

        mainViewModel.bookmarksLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mainViewModel.getBookmarks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}