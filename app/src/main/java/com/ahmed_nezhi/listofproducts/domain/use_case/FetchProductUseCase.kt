package com.ahmed_nezhi.listofproducts.domain.use_case

import com.ahmed_nezhi.listofproducts.common.NetworkUtils
import com.ahmed_nezhi.listofproducts.common.Resource
import com.ahmed_nezhi.listofproducts.data.local.LeBonCoinDatabase
import com.ahmed_nezhi.listofproducts.data.mapper.toProduct
import com.ahmed_nezhi.listofproducts.data.mapper.toProductEntity
import com.ahmed_nezhi.listofproducts.domain.model.Product
import com.ahmed_nezhi.listofproducts.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Create by A.Nezhi on 09/10/2023.
 */
open class FetchProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val db: LeBonCoinDatabase,
    private val networkUtils: NetworkUtils
) {
    // we use invoke method in order to have the possibility to call GetProductUseCase as a function
    operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            if (networkUtils.isInternetAvailable()) {
                val products = repository.getProductsFromRemote()
                withContext(Dispatchers.IO) {
                    // convert productDto to ProductEntity
                    db.productDao.insertProducts(products.map { it.toProductEntity() })
                }
                emit(Resource.Success(products.map { it.toProduct() }))
            } else {
                val products = withContext(Dispatchers.IO) {
                    db.productDao.listOfProducts()
                }
                emit(Resource.Success(products.map { it.toProduct() }))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpeted error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server"))
        }
    }
}