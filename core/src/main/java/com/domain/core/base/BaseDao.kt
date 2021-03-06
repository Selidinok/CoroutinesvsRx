package com.domain.core.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.*

/**
 * Created by User on 21:19 29.09.2018.

 */
abstract class BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun bulkInsert(vararg obj: Entity): List<Long>

    @Update
    abstract fun update(obj: Entity)

    @Delete
    abstract fun delate(obj: Entity)

    /**
     * LiveData that propagates only distinct emissions.
     */
    fun <T> LiveData<T>.getDistinct(): LiveData<T> {
        val distinctLiveData = MediatorLiveData<T>()
        distinctLiveData.addSource(this, object : Observer<T> {
            private var initialized = false
            private var lastObj: T? = null

            override fun onChanged(obj: T?) {
                if (!initialized) {
                    initialized = true
                    lastObj = obj
                    distinctLiveData.postValue(lastObj)
                } else if ((obj == null && lastObj != null) || obj != lastObj) {
                    lastObj = obj
                    distinctLiveData.postValue(lastObj)
                }
            }
        })

        return distinctLiveData
    }
}
