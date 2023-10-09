package com.ahmed_nezhi.listofproducts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmed_nezhi.listofproducts.data.local.dao.ProductDao
import com.ahmed_nezhi.listofproducts.data.local.entity.ProductEntity

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Database(entities = [ProductEntity::class], version = 1)
abstract class LeBonCoinDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}