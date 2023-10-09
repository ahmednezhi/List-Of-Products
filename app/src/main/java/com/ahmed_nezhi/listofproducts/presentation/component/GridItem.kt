package com.ahmed_nezhi.listofproducts.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmed_nezhi.listofproducts.presentation.ProductPreviewScreen
import com.ahmed_nezhi.listofproducts.presentation.viewmodel.ProductViewModel

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Composable
fun GridItem(title: String, imageUrl: String, placeHolder: String) {
    val productViewModel = viewModel<ProductViewModel>()

    // Use a unique key for each GridItem based on its title to ensure correct data
    val itemKey = "GridItem:$title"

    val showBottomSheetState = productViewModel.getShowBottomSheetState(itemKey)
    val toggleBottomSheet = { productViewModel.toggleBottomSheet(itemKey) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clickable {
                // Toggle the bottom sheet state for this specific GridItem
                toggleBottomSheet()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            AsyncImageView(placeHolder, title, 1f)
            Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }

    if (showBottomSheetState.value) {
        ProductPreviewScreen(imageUrl = imageUrl, text = title, onDismiss = {
            // Toggle the bottom sheet state for this specific GridItem when dismissed
            toggleBottomSheet()
        })
    }
}
