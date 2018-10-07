package com.domain.core.base

/**
 * Created by User on 17:09 30.09.2018.

 */
interface BaseMapper<D, C, R> {
    fun fromRemote(response: R): D
//    fun toRemote(domain: D): R

    fun fromCache(cache: C): D
//    fun toCache(domain: D): C

    fun fromRemoteToCache(response: R): C
}