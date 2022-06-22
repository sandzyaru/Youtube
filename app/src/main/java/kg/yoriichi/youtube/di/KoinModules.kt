package kg.yoriichi.youtube.di

import kg.yoriichi.youtube.core.network.networkModules
import kg.yoriichi.youtube.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModules,
    remoteDataSource
)