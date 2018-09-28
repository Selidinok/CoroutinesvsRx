package com.cashback.core.base

import org.mapstruct.factory.Mappers
import timber.log.Timber

class MapperDelegate<M>(clazz: Class<M>) {
    var mapper = Mappers.getMapper(clazz) ?: Timber.e("Can't find mapper class: ${clazz.simpleName}")
}