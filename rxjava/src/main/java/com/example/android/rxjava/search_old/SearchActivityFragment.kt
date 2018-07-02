package com.example.android.rxjava.search_old

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.rxjava.R
import kotlinx.android.synthetic.main.fragment_search_activity.*
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import nl.ippies.presentation.search.SearchPresenter

/**
 * A placeholder fragment containing a simple view.
 */
class SearchActivityFragment : Fragment() {
    private val broadcast = ConflatedBroadcastChannel<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val presenter = SearchPresenter(broadcast, this)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty()) {
                    broadcast.offer(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    public fun showContent(show: Boolean) {

    }
}
