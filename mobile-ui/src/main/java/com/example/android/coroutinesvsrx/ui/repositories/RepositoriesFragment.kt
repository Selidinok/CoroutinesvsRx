package com.example.android.coroutinesvsrx.ui.repositories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.coroutinesvsrx.adapters.RepositoriesAdapter
import com.example.android.coroutinesvsrx.databinding.FragmentRepositoriesListBinding
import com.example.android.coroutinesvsrx.viewmodels.repositories.RepositoriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Artem Rumyantsev on 13:29 06.10.2018.

 */
class RepositoriesFragment : Fragment() {

    val viewModel: RepositoriesViewModel by viewModel()
    val adapter: RepositoriesAdapter by lazy { RepositoriesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        bindScope(getOrCreateScope("repositoriesScope"))
        val binding = FragmentRepositoriesListBinding.inflate(inflater, container, false)

        binding.repositoriesList.adapter = adapter
        viewModel.getRepositoriesList().observe(viewLifecycleOwner, Observer {
            if (it != null) adapter.setData(it)
        })

        return binding.root
    }
}