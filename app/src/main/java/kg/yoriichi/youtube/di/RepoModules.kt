package kg.yoriichi.youtube.di

import kg.yoriichi.youtube.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}