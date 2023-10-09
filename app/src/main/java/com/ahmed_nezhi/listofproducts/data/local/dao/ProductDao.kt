package com.ahmed_nezhi.listofproducts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmed_nezhi.listofproducts.data.local.entity.ProductEntity

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM productentity")
    fun listOfProducts(): List<ProductEntity>
}