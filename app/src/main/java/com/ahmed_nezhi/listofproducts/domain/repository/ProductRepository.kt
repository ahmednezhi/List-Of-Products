package com.ahmed_nezhi.listofproducts.domain.repository

import com.ahmed_nezhi.listofproducts.data.local.entity.ProductEntity
import com.ahmed_nezhi.listofproducts.data.remote.dto.ProductDto

/**
 * Create by A.Nezhi on 09/10/2023.
 */
interface ProductRepository {

    suspend fun getProductsFromRemote(): List<ProductDto>
    suspend fun getProductsFromLocal(): List<ProductEntity>

}