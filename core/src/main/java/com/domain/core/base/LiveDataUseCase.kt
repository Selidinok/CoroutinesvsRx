package com.domain.core.base

import android.arch.lifecycle.LiveData

/**
 * Created by Artem Rumyantsev on 20:18 06.10.2018.

 */
abstract class LiveDataUseCase<Type, in Params> where Type : Any {

    abstract fun getLiveData(params: Params): LiveData<Type>

    operator fun invoke(params: Params): LiveData<Type> = getLiveData(params)
}