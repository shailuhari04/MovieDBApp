package com.droidplusplus.moviedbapp.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

open class BaseViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRuleTest()

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}