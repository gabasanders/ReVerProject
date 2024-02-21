package com.example.reverproject.data.source.local

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reverproject.data.Card
import java.util.Date

@Entity(
    tableName = "card"
)
data class LocalCard(
    @PrimaryKey val id: String,
    var title: String,
    var text: String,
    //var created: Date,
    var progress : Int,
)

fun LocalCard.toExternal() = Card(
    id = id,
    title = title,
    text = text,
    progress = progress,
)

fun List<LocalCard>.toExternal() = map(LocalCard::toExternal)

fun Card.toLocal() = LocalCard (
    id = id,
    title = title,
    text = text,
    progress = progress,
)