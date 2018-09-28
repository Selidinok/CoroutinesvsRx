package com.cashback.data.mappers.repositories

import com.cashback.core.base.BaseRemoteMapper
import com.cashback.data.entities.domain.Repository
import com.cashback.data.entities.remote.RepositoryResponse
import org.mapstruct.Mapper

@Mapper
interface RepositoriesMapper : BaseRemoteMapper<Repository, RepositoryResponse> {
    override fun fromRemote(response: RepositoryResponse): Repository

    override fun toRemote(domain: Repository): RepositoryResponse
}