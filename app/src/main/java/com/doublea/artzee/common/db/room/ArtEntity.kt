package com.doublea.artzee.common.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art")
data class ArtEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val category: String,
    val medium: String?,
    val date: String?,
    val collectingInstitution: String,
    val thumbnail: String,
    val image: String,
    val imageRectangle: String,
    val artistId: String?
)


