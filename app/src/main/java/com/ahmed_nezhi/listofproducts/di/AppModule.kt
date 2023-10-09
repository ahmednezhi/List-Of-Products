package com.ahmed_nezhi.listofproducts.di

import android.content.Context
import androidx.room.Room
import com.ahmed_nezhi.listofproducts.common.Constants
import com.ahmed_nezhi.listofproducts.common.Constants.DATABASE_NAME
import com.ahmed_nezhi.listofproducts.common.NetworkUtils
import com.ahmed_nezhi.listofproducts.data.local.LeBonCoinDatabase
import com.ahmed_nezhi.listofproducts.data.remote.api.ProductApi
import com.ahmed_nezhi.listofproducts.data.repository.ProductRepositoryImpl
import com.ahmed_nezhi.listofproducts.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideAccountRepository(api: ProductApi, db: LeBonCoinDatabase): ProductRepository =
        ProductRepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideLeBonCoinDatabase(@ApplicationContext context: Context): LeBonCoinDatabase {
        return Room.databaseBuilder(context, LeBonCoinDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

}