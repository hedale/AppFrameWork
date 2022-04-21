package com.hele.mrd.app.lib.router

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityResultDto<T: Parcelable>(
    val data: T? = null,

    val dataArray: ArrayList<T>? = null,
): Parcelable

@Parcelize
data class StringDto(
    val content: String,
) : Parcelable

@Parcelize
data class ArrayListStringDto(
    val content: ArrayList<String>
): Parcelable

@Parcelize
data class IntDto(
    val content: Int,
) : Parcelable

@Parcelize
data class FloatDto(
    val content: Float,
) : Parcelable

@Parcelize
data class DoubleDto(
    val content: Double,
) : Parcelable

@Parcelize
data class LongDto(
    val content: Long,
) : Parcelable

@Parcelize
data class ByteDto(
    val content: Byte,
) : Parcelable

@Parcelize
data class ShortDto(
    val content: Short,
) : Parcelable

@Parcelize
data class BooleanDto(
    val content: Boolean,
) : Parcelable
