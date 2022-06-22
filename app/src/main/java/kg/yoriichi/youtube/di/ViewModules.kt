package kg.yoriichi.youtube.di

import kg.yoriichi.youtube.ui.playlist.PlaylistViewModel
import kg.yoriichi.youtube.ui.playlist_detail.PlaylistDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules : Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistDetailViewModel(get()) }
}