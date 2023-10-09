package com.ahmed_nezhi.listofproducts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Entity
data class ProductEntity(
    @PrimaryKey var id: Int,
    var albumId: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)