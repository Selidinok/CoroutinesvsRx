package com.example.android.coroutinesvsrx.ui.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.coroutinesvsrx.databinding.FragmentSearchBinding

/**
 * Created by Artem Rumyantsev on 13:45 06.10.2018.

 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        activity?.actionBar?.setHomeButtonEnabled(true)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)
        return binding.root
    }
}