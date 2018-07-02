package com.example.android.coroutinesvsrx

import android.os.Bundle
import android.app.Activity
import android.text.Editable
import android.text.TextWatcher

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel

class SearchActivity : Activity() {
    private val broadcast = ConflatedBroadcastChannel<String>()
    private lateinit var presenter : SearchPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty()) {
                    broadcast.offer(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        presenter = SearchPresenter(this, broadcast)
    }

    fun showContent(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
