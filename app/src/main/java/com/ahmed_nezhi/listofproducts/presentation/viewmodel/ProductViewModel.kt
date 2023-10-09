package com.ahmed_nezhi.listofproducts.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_nezhi.listofproducts.common.NetworkUtils
import com.ahmed_nezhi.listofproducts.common.Resource
import com.ahmed_nezhi.listofproducts.domain.use_case.FetchProductUseCase
import com.ahmed_nezhi.listofproducts.presentation.util.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    val fetchProductUseCase: FetchProductUseCase,
    val networkUtils: NetworkUtils
) : ViewModel() {

    // to avoid modifying the content of state from the activity or fragment
    private val _productListState = MutableStateFlow(ProductListState())

    // to avoid modifying the content of state from the activity or fragment
    private val _isInternetAvailableState = mutableStateOf(true)

    // expose only this
    val productListState: StateFlow<ProductListState> = _productListState
    val isInternetAvailable: MutableState<Boolean> = _isInternetAvailableState

    // Map of item keys (e.g., "GridItem:title") to showBottomSheet states
    private val showBottomSheetStates = mutableStateMapOf<String, MutableState<Boolean>>()

    init {
        getProducts()
        updateNetworkState()
    }

    private fun updateNetworkState() {
        _isInternetAvailableState.value = networkUtils.isInternetAvailable()
    }

    // Function to get the showBottomSheet state for a specific item
    fun getShowBottomSheetState(itemKey: String): MutableState<Boolean> {
        return showBottomSheetStates.getOrPut(itemKey) { mutableStateOf(false) }
    }

    // Function to toggle the showBottomSheet state for a specific item
    fun toggleBottomSheet(itemKey: String) {
        showBottomSheetStates[itemKey]?.value = !showBottomSheetStates[itemKey]?.value!!
    }

    private fun getProducts() {
        fetchProductUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _productListState.value = ProductListState(isLoading = true)
                }

                is Resource.Success -> {
                    _productListState.value = ProductListState(products = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _productListState.value = ProductListState(
                        error = it.message ?: "An unexpected error-"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun reloadData() {
        getProducts()
    }
}