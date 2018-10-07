package com.domain.mappers.repositories

import com.domain.core.base.BaseMapper
import com.domain.entities.Owner
import com.example.android.cache.entity.OwnerEntity
import com.example.android.remote.entity.RepositoryResponse

/**
 * Created by Artem Rumyantsev on 15:16 07.10.2018.

 */
object OwnerMapper : BaseMapper<Owner, OwnerEntity, RepositoryResponse.Owner> {
    override fun fromRemote(response: RepositoryResponse.Owner): Owner = Owner(
        response.id,
        response.login,
        response.nodeId,
        response.avatarUrl,
        response.gravatarId,
        response.url
    )

    override fun fromCache(cache: OwnerEntity): Owner = Owner(
        cache.id,
        cache.login,
        cache.nodeId,
        cache.avatarUrl,
        cache.gravatarId,
        cache.url
    )

    override fun fromRemoteToCache(response: RepositoryResponse.Owner): OwnerEntity = OwnerEntity(
        response.id,
        response.login,
        response.nodeId,
        response.avatarUrl,
        response.gravatarId,
        response.url
    )
}