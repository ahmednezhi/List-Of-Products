package com.ahmed_nezhi.listofproducts.presentation.util

import com.ahmed_nezhi.listofproducts.domain.model.Product

/**
 * Create by A.Nezhi on 09/10/2023.
 */
data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)