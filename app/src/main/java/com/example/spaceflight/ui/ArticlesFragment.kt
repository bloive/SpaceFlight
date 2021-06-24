package com.example.spaceflight.ui

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflight.R
import com.example.spaceflight.adapters.ArticleRecycler
import com.example.spaceflight.databinding.ArticlesFragmentBinding
import com.example.spaceflight.models.Article
import com.example.spaceflight.models.ArticleItem

class ArticlesFragment : Fragment() {

    private val articlesViewModel by viewModels<ArticlesViewModel>()
    private var _binding: ArticlesFragmentBinding? = null
    private lateinit var adapter: ArticleRecycler

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticlesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        observe()
        articlesViewModel.init()
    }

    override fun onResume() {
        super.onResume()
        d("STATE: ", "Resume")
        articlesViewModel.intervalCall(5000)
    }

    override fun onStop() {
        super.onStop()
        d("STATE: ", "Stop")
        articlesViewModel.job?.cancel()
        articlesViewModel.job = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewPager() {
        adapter = ArticleRecycler()
        binding.articleViewpager.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun observe() {
        articlesViewModel.newsLiveData.observe(viewLifecycleOwner, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })
    }
}