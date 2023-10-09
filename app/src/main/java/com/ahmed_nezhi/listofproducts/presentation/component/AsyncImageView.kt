package com.ahmed_nezhi.listofproducts.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.ahmed_nezhi.listofproducts.R

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@Composable
fun AsyncImageView(url: String, contentDescription: String, ratio: Float? = null) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.baseline_wifi_tethering_error_100),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .let {
                            if (ratio != null) {
                                it
                                    .aspectRatio(ratio)
                                    .fillMaxSize()
                            } else {
                                it
                            }
                        }
                )
            }

            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            else -> {
                // Image loaded successfully, display it
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .let {
                            if (ratio != null) {
                                it
                                    .aspectRatio(ratio)
                                    .fillMaxSize()
                            } else {
                                it
                            }
                        }
                )
            }
        }
    }
}

