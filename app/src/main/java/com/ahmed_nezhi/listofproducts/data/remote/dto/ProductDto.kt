package com.ahmed_nezhi.listofproducts.data.remote.dto

/**
 * Create by A.Nezhi on 09/10/2023.
 */
data class ProductDto(
    var id: Int,
    var albumId: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)
