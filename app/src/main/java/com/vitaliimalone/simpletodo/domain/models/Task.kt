package com.vitaliimalone.simpletodo.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import java.util.UUID

@Parcelize
data class Task(
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var modifiedAt: OffsetDateTime = OffsetDateTime.now(),
    var dueTo: OffsetDateTime = OffsetDateTime.now().with(LocalTime.MAX),
    var isDone: Boolean = false,
    var isArchived: Boolean = false,
    var tags: MutableList<String> = mutableListOf(),
    var subtasks: MutableList<Subtask> = mutableListOf()
) : Parcelable