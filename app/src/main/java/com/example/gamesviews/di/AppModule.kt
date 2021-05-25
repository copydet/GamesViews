package com.example.gamesviews.di

import com.example.core.domain.usecase.GamesInteractor
import com.example.core.domain.usecase.GamesUseCase
import com.example.gamesviews.ui.detail.DetailGamesViewModel
import com.example.gamesviews.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    factory {
        Dispatchers.IO
    }
    viewModel {
        HomeViewModel(get()) }
    viewModel {
        DetailGamesViewModel(get(), get()) }
}