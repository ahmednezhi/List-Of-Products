package com.ahmed_nezhi.listofproducts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ahmed_nezhi.listofproducts.R
import com.ahmed_nezhi.listofproducts.presentation.component.LazyGridList
import com.ahmed_nezhi.listofproducts.presentation.viewmodel.ProductViewModel

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Composable
fun ProductListScreen(viewModel: ProductViewModel) {
    val productListState by viewModel.productListState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (productListState.isLoading) {
            CircularProgressIndicator()
        } else {
            if (productListState.error.isNotEmpty() || productListState.products.isEmpty()) {
                if (viewModel.isInternetAvailable.value) {
                    Text(text = stringResource(id = R.string.no_item_error))
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.no_net))
                        Button(onClick = { viewModel.reloadData() }) {
                            Text(text = stringResource(id = R.string.reload))
                        }
                    }
                }
            } else {
                Column {
                    Text(
                        text = stringResource(id = R.string.list_of_product_title),
                        modifier = Modifier.padding(24.dp)
                    )
                    LazyGridList(productListState.products)
                }
            }
        }
    }

}




