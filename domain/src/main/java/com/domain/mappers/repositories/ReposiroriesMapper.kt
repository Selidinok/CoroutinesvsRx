package com.domain.mappers.repositories

import com.domain.core.base.BaseMapper
import com.domain.entities.cash.RepositoryEntity
import com.domain.entities.domain.Repository
import com.domain.entities.remote.RepositoryResponse
import org.mapstruct.Mapper

/**
 * Created by User on 18:05 30.09.2018.

 */
@Mapper
interface ReposiroriesMapper : BaseMapper<Repository, RepositoryEntity, RepositoryResponse> {
    override fun fromRemote(response: RepositoryResponse): Repository

    override fun toRemote(domain: Repository): RepositoryResponse

    override fun fromCache(cache: RepositoryEntity): Repository
    override fun toCache(domain: Repository): RepositoryEntity

    override fun fromRemoteToCache(remote: RepositoryResponse): RepositoryEntity
}