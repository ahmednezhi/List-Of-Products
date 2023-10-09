package com.ahmed_nezhi.listofproducts.data.remote.api

import com.ahmed_nezhi.listofproducts.common.Constants.PRODUCT_END_POINT
import com.ahmed_nezhi.listofproducts.data.remote.dto.ProductDto
import retrofit2.http.GET

/**
 * Create by A.Nezhi on 09/10/2023.
 */
interface ProductApi {

    @GET(PRODUCT_END_POINT)
    suspend fun getProducts(): List<ProductDto>

}