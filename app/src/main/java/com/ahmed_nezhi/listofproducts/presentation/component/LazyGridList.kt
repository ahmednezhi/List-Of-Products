package com.ahmed_nezhi.listofproducts.presentation.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.ahmed_nezhi.listofproducts.domain.model.Product

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Composable
fun LazyGridList(products: List<Product>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(products) { product ->
            GridItem(product.title, product.url, product.thumbnailUrl)
        }
    }
}