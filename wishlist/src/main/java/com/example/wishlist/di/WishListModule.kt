package com.example.wishlist.di

import com.example.wishlist.WishListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishListModule = module {
    viewModel { WishListViewModel(get(), get()) }
}