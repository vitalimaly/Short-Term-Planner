package com.vitaliimalone.simpletodo.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import java.util.*

@Parcelize
data class Task(
        var id: String = UUID.randomUUID().toString(),
        var title: String = "",
        var description: String = "",
        var createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now(),
        var dueTo: LocalDateTime = LocalDateTime.now().with(LocalTime.MAX),
        var isDone: Boolean = false,
        var isArchived: Boolean = false
) : Parcelable