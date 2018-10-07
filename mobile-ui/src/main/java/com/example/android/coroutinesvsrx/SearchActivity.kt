package com.example.android.coroutinesvsrx

import android.app.Activity
import android.os.Bundle
import com.domain.core.result.Failure
import com.example.android.coroutinesvsrx.presenter.SearchPresenter
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel

class SearchActivity : Activity() {
    private val broadcast = ConflatedBroadcastChannel<String>()
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search)

//        editText.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                if (!p0.isNullOrEmpty()) {
//                    broadcast.offer(p0.toString())
//                }
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//        })
        presenter = SearchPresenter(this, broadcast)
    }

    fun showContent(show: Boolean) {
//        merchantsTextView.text = ""
//        dealsTextView.text = ""
    }

//    fun showMerchants(merchants: List<Merchant>) {
//        merchantsTextView.text = merchants.toString()
//    }

    fun showMerchantError(failure: Failure) {
        when (failure) {
//            is Failure.ServerError -> merchantsTextView.text = "ServerError"
//            is Failure.NetworkConnection -> merchantsTextView.text = "Offline"
        }
    }

//    fun showDeals(deals: List<Deal>) {
//        dealsTextView.text = deals.toString()
//    }

    fun showDealsError(failure: Failure) {
        when (failure) {
//            is Failure.ServerError -> dealsTextView.text = "ServerError"
//            is Failure.NetworkConnection -> dealsTextView.text = "Offline"
        }
    }

    fun showProgress(show: Boolean) {
        if (show) {
//            merchantsTextView.text = "Progress"
//            dealsTextView.text = "Progress"
        } else {
        }
    }

}
