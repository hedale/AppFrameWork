package com.hele.mrd.app.lib.base.dataclass

data class BaseLiveDataDto<T>(
    val data: T? = null,
    val e: Exception? = null,
)