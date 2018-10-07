package com.domain.mappers.repositories

import com.domain.core.base.BaseMapper
import com.domain.entities.Repository
import com.example.android.cache.entity.RepositoryEntity
import com.example.android.remote.entity.RepositoryResponse

/**
 * Created by User on 18:05 30.09.2018.

 */
class ReposiroriesMapper : BaseMapper<Repository, RepositoryEntity, RepositoryResponse> {
    override fun fromRemote(response: RepositoryResponse): Repository = Repository(
        response.id,
        response.nodeId,
        response.name,
        response.fullName,
        response.owner.id
    )

    override fun fromCache(cache: RepositoryEntity): Repository = Repository(
        cache.id,
        cache.nodeId,
        cache.name,
        cache.fullName,
        cache.ownerId
    )

    override fun fromRemoteToCache(response: RepositoryResponse): RepositoryEntity =
        RepositoryEntity(
            response.id,
            response.nodeId,
            response.name,
            response.fullName,
            response.owner.id
        )
}