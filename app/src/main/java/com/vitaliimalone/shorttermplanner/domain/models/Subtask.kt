package com.vitaliimalone.shorttermplanner.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime
import java.util.UUID

@Parcelize
data class Subtask(
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = LocalDateTime.now(),
    var isDone: Boolean = false
) : Parcelable