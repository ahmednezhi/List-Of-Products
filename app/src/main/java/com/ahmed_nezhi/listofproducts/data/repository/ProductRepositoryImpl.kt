package com.ahmed_nezhi.listofproducts.data.repository

import com.ahmed_nezhi.listofproducts.data.local.LeBonCoinDatabase
import com.ahmed_nezhi.listofproducts.data.local.entity.ProductEntity
import com.ahmed_nezhi.listofproducts.data.remote.api.ProductApi
import com.ahmed_nezhi.listofproducts.data.remote.dto.ProductDto
import com.ahmed_nezhi.listofproducts.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Create by A.Nezhi on 09/10/2023.
 */
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val db: LeBonCoinDatabase
) : ProductRepository {

    override suspend fun getProductsFromRemote(): List<ProductDto> {
        return api.getProducts()
    }

    override suspend fun getProductsFromLocal(): List<ProductEntity> {
        return db.productDao.listOfProducts()
    }
}