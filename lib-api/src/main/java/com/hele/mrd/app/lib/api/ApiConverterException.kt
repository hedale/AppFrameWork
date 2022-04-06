package com.hele.mrd.app.lib.api

class ApiConverterException(val code: Int, override val message: String?, override val cause: Throwable?, val ext: Any?) : Exception(message, cause) {
}