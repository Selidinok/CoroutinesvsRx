package com.domain.core.cash

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.domain.core.base.BaseDao

/**
 * Created by User on 22:10 29.09.2018.

 */
@Dao
abstract class CashDao : BaseDao<CacheEntity>() {


    @Query("SELECT * FROM CacheEntity WHERE tableName = :tableName")
    abstract fun getCash(tableName: String): CacheEntity?
}