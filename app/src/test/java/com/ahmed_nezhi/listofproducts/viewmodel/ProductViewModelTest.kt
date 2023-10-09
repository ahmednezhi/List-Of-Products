package com.ahmed_nezhi.listofproducts.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmed_nezhi.listofproducts.common.NetworkUtils
import com.ahmed_nezhi.listofproducts.common.Resource
import com.ahmed_nezhi.listofproducts.domain.model.Product
import com.ahmed_nezhi.listofproducts.domain.repository.ProductRepository
import com.ahmed_nezhi.listofproducts.domain.use_case.FetchProductUseCase
import com.ahmed_nezhi.listofproducts.presentation.viewmodel.ProductViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Create by A.Nezhi on 09/10/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var productViewModel: ProductViewModel
    private lateinit var mockGetProductUseCase: FetchProductUseCase
    private lateinit var mockNetworkUtils: NetworkUtils
    private lateinit var mockRepository: ProductRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockGetProductUseCase = mockk(relaxed = true)
        mockNetworkUtils = mockk(relaxed = true)
        mockRepository = mockk(relaxed = true)

        productViewModel = ProductViewModel(mockGetProductUseCase, mockNetworkUtils)

    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state of ViewModel`() {
        val initialState = productViewModel.productListState.value
        Assert.assertTrue(initialState.products.isEmpty())
        Assert.assertTrue(!initialState.isLoading)
        Assert.assertTrue(initialState.error.isEmpty())
    }

    @Test
    fun `test loading state when fetching products`() = TestScope().runTest {
        // Given
        every { mockGetProductUseCase() } returns flow { emit(Resource.Loading()) }

        // When
        productViewModel.reloadData()
        //Advances the testScheduler to the point where there are no tasks remaining.
        testScope.advanceUntilIdle()

        // Then
        Assert.assertTrue(productViewModel.productListState.value.isLoading)
    }

    @Test
    fun `test successful fetch of products`() = TestScope().runTest {
        // Given
        val mockProducts = createFakeProducts()
        every { mockGetProductUseCase.invoke() } returns flowOf(Resource.Success(mockProducts))

        // When
        productViewModel.reloadData()
        testScope.advanceUntilIdle()

        // Then
        Assert.assertEquals(mockProducts, productViewModel.productListState.value.products)
        Assert.assertFalse(productViewModel.productListState.value.isLoading)
        Assert.assertTrue(productViewModel.productListState.value.error.isEmpty())
    }


    @Test
    fun `test error state when fetching products`() = TestScope().runTest {
        // Given
        val errorMessage = "An error occurred"
        every { mockGetProductUseCase.invoke() } returns flowOf(Resource.Error(errorMessage))

        // When
        productViewModel.reloadData()
        testScope.advanceUntilIdle()

        // Then
        Assert.assertEquals(errorMessage, productViewModel.productListState.value.error)
        Assert.assertFalse(productViewModel.productListState.value.isLoading)
    }


    @Test
    fun `test reloadData function`() = TestScope().runTest {
        // Given
        val mockProducts = createFakeProducts()
        every { mockGetProductUseCase.invoke() } returns flowOf(Resource.Success(mockProducts))

        // When
        productViewModel.reloadData()
        testScope.advanceUntilIdle()

        // Then
        Assert.assertEquals(mockProducts, productViewModel.productListState.value.products)
    }


    // Helper method to create fake data for testing
    private fun createFakeProducts(): List<Product> {
        val product1 =
            Product(
                1,
                1,
                "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952",
                "https://via.placeholder.com/150/92c952"
            )
        val product2 =
            Product(
                1,
                2,
                "accusamus beatae ad",
                "https://via.placeholder.com/600/92c952",
                "https://via.placeholder.com/150/92c952"
            )
        return listOf(
            product1, product2
        )
    }

}