package com.ahmed_nezhi.listofproducts.data.mapper

import com.ahmed_nezhi.listofproducts.data.local.entity.ProductEntity
import com.ahmed_nezhi.listofproducts.data.remote.dto.ProductDto
import com.ahmed_nezhi.listofproducts.domain.model.Product

/**
 * Create by A.Nezhi on 09/10/2023.
 */
fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(id, albumId, title, url, thumbnailUrl)
}

fun ProductDto.toProduct(): Product {
    return Product(id, albumId, title, url, thumbnailUrl)
}

fun ProductEntity.toProduct(): Product {
    return Product(id, albumId, title, url, thumbnailUrl)
}