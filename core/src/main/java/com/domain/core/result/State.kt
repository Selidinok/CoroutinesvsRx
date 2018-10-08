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
fun stateToString(state: State?) =
    if (state != null) state::class.simpleName ?: ""
    else ""
