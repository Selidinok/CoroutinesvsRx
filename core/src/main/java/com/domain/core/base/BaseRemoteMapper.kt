package com.domain.core.base

interface BaseRemoteMapper<Domain, Response> {
    fun fromRemote(response: Response): Domain
    fun toRemote(domain: Domain): Response
}