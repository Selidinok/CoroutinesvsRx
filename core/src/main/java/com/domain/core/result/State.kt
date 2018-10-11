package com.domain.core.result

import android.databinding.BindingConversion

/**
 * Created by User on 18:49 30.06.2018.

 */
sealed class State {
    data class Failure(val message: String?) : State()
    object Success : State()
    object Loading : State()
    object Empty : State()
}

@BindingConversion
fun stateToString(state: State?): String = when (state) {
    is State.Failure -> state.message ?: ""
    is State.Success -> State.Success::class.java.simpleName
    is State.Loading -> State.Loading::class.java.simpleName
    is State.Empty -> State.Empty::class.java.simpleName
    else -> ""
}

