package com.domain.core.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations

/**
 * Created by User on 12:00 30.09.2018.

 */
abstract class BaseDataRepository<D, C, R>(
    private val cacheRepository: BaseCacheRepository,
    private val remoteRepository: BaseRemoteRepository,
    private val mapper: BaseMapper<D, C, R>
) {

    fun get(
        update: Boolean,
        cashEntity: () -> C,
        remoteEntity: () -> R,
        saveEntity: (R) -> Unit
    ): D {
        if (update) {
            saveEntity(remoteEntity())
        }
        return mapper.fromCache(cashEntity())
    }

    //    fun Cache.mapToDomain() = mapper.fromCache(this)
//
    fun <T, R> transformLiveData(obj: LiveData<T>, mapper: (T) -> R): LiveData<R> =
        Transformations.switchMap(obj) {
            val newData = MutableLiveData<R>()
            newData.value = mapper(it)
            return@switchMap newData
        }
}