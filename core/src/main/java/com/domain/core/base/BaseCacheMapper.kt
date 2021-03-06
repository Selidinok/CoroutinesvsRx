package com.domain.core.base

interface BaseCacheMapper<Domain, Cache> {
    fun fromCache(cache: Cache): Domain
    fun toCache(domain: Domain): Cache
}